/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
@file:Suppress("UnnecessaryVariable")

package moe.sdl.suzuyoi.proto.processor

import com.google.devtools.ksp.processing.*
import com.google.devtools.ksp.symbol.KSAnnotated
import com.google.devtools.ksp.symbol.KSClassDeclaration
import com.google.devtools.ksp.symbol.KSFile
import com.google.devtools.ksp.symbol.KSFunctionDeclaration
import com.squareup.kotlinpoet.*
import com.squareup.kotlinpoet.ParameterizedTypeName.Companion.parameterizedBy
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import com.squareup.wire.Message
import com.squareup.wire.WireRpc
import okio.ByteString
import java.io.OutputStream

private const val DOLLAR = "$"
private val WIRE_RPC_CLASS_NAME = WireRpc::class.qualifiedName!!
private val NOTHING = ClassName("kotlin", "Nothing")

data class WireRpcData(
  val path: String,
  val requestAdapter: String,
  val responseAdapter: String,
  val methodName: String,
)

class Processor(
  private val options: Map<String, String>,
  private val logger: KSPLogger,
  private val codeGenerator: CodeGenerator,
) : SymbolProcessor {
  operator fun OutputStream.plusAssign(str: String) {
    this.write(str.toByteArray())
  }

  @Suppress("UNCHECKED_CAST")
  override fun process(resolver: Resolver): List<KSAnnotated> {
    val clzToFuncs =
      resolver
        .getSymbolsWithAnnotation(WIRE_RPC_CLASS_NAME)
        .filterIsInstance<KSFunctionDeclaration>()
        .groupBy {
          it.parentDeclaration
        }.mapNotNull { (parentDecl, funcs) ->
          val parentClz =
            (parentDecl as? KSClassDeclaration)
              ?: return@mapNotNull null
          parentClz to funcs
        }

    if (clzToFuncs.none()) return emptyList()

    val usedKSFile = mutableSetOf<KSFile>()

    val funcSpecs =
      clzToFuncs.flatMap { (clz, funcs) ->
        clz.containingFile?.also { usedKSFile += it }
        val wireRpcAnnotations = transformWireRpcAnnotations(funcs)
        buildList {
          addAll(generateHandleRequestFuncs(clz, wireRpcAnnotations))
          addAll(
            generateParsePacketFuncs(clz, wireRpcAnnotations, "Request") {
              it.requestAdapter.replace("#", ".")
            },
          )
          addAll(
            generateParsePacketFuncs(clz, wireRpcAnnotations, "Response") {
              it.responseAdapter.replace("#", ".")
            },
          )
        }
      }

    val fileSpec =
      FileSpec
        .builder("moe.sdl.suzuyoi.protos", "MethodMap")
        .addKotlinDefaultImports(includeJs = false)
        .addFunctions(funcSpecs)
        .build()

    fileSpec.writeTo(codeGenerator, Dependencies(false, *usedKSFile.toTypedArray()))

    return emptyList()
  }

  private fun transformWireRpcAnnotations(funcs: List<KSFunctionDeclaration>): List<WireRpcData> =
    funcs.map { func ->
      val wireRpcAnno = func.annotations.first { it.shortName.getShortName() == "WireRpc" }
      val args =
        wireRpcAnno.arguments.associate { arg ->
          val name = arg.name!!.getShortName()
          val value =
            arg.value as? String
              ?: throw IllegalStateException("$name is not an instance of String: ${wireRpcAnno.location}")
          name to value
        }

      assert(args.containsKey("path")) { "The `@WireRpc` annotation does not contains `path` argument: ${wireRpcAnno.location}" }
      assert(
        args.containsKey("requestAdapter"),
      ) { "The `@WireRpc` annotation does not contains `requestAdapter` argument ${wireRpcAnno.location}" }
      assert(
        args.containsKey("responseAdapter"),
      ) { "The `@WireRpc` annotation does not contains `responseAdapter` argument ${wireRpcAnno.location}" }
      WireRpcData(
        args["path"]!!,
        args["requestAdapter"]!!,
        args["responseAdapter"]!!,
        func.simpleName.getShortName(),
      )
    }

  private fun generateHandleRequestFuncs(
    clz: KSClassDeclaration,
    wireRpcAnnotations: List<WireRpcData>,
  ): List<FunSpec> {
    val clzName = clz.simpleName.getShortName()
    val handleRequestFN = "handle${clzName}Request"
    val handleRequestOrNullFN = "${handleRequestFN}OrNull"

    var handleRequestOrNullFunc =
      FunSpec
        .builder(handleRequestOrNullFN)
        .addModifiers(KModifier.SUSPEND)
        .addParameter("server", clz.toClassName())
        .addParameter("methodName", String::class.asTypeName())
        .addParameter("data", ByteString::class.asTypeName())
        .returns(ByteString::class.asTypeName().copy(nullable = true))
        .beginControlFlow("return when (methodName)")

    wireRpcAnnotations.forEach {
      handleRequestOrNullFunc =
        handleRequestOrNullFunc.beginControlFlow(
          """"${it.path.replace("/", ".")}" ->""",
        )
      val requestAdapter = it.requestAdapter.replace("#", ".")
      handleRequestOrNullFunc =
        handleRequestOrNullFunc.addStatement(
          "server.${it.methodName}($requestAdapter.decode(data)).encodeByteString()",
        )
      handleRequestOrNullFunc.endControlFlow()
    }

    handleRequestOrNullFunc =
      handleRequestOrNullFunc
        .beginControlFlow("else ->")
        .addStatement("""null""")
        .endControlFlow()

    handleRequestOrNullFunc.endControlFlow()

    val handleRequestFunc =
      FunSpec
        .builder(handleRequestFN)
        .addModifiers(KModifier.SUSPEND)
        .addParameter("server", clz.toClassName())
        .addParameter("methodName", String::class.asTypeName())
        .addParameter("data", ByteString::class.asTypeName())
        .returns(ByteString::class.asTypeName())
        .addCode(
          """
          when (val result = $handleRequestOrNullFN(server, methodName, data)) {
            null -> throw IllegalStateException("Unknown method ${DOLLAR}methodName")
            else -> return result
          }
          """.trimIndent(),
        )

    return listOf(handleRequestOrNullFunc.build(), handleRequestFunc.build())
  }

  private fun generateParsePacketFuncs(
    clz: KSClassDeclaration,
    wireRpcAnnotations: List<WireRpcData>,
    packetPrefix: String,
    selectAdapter: (WireRpcData) -> String,
  ): List<FunSpec> {
    val clzName = clz.simpleName.getShortName()
    val parsePacketFN = "parse${clzName}${packetPrefix}Packet"
    val parsePacketOrNullFN = "${parsePacketFN}OrNull"

    var parsePacketOrNullFunc =
      FunSpec
        .builder(parsePacketOrNullFN)
        .addParameter("methodName", String::class.asTypeName())
        .addParameter("data", ByteString::class.asTypeName())
        .returns(
          // Message<*, Nothing>?
          Message::class
            .asClassName()
            .parameterizedBy(STAR, NOTHING)
            .copy(nullable = true),
        ).beginControlFlow("return when (methodName)")

    wireRpcAnnotations.forEach {
      parsePacketOrNullFunc =
        parsePacketOrNullFunc.beginControlFlow(
          """"${it.path.replace("/", ".")}" ->""",
        )
      val adapter = selectAdapter(it)
      parsePacketOrNullFunc =
        parsePacketOrNullFunc.addStatement(
          "$adapter.decode(data)",
        )
      parsePacketOrNullFunc.endControlFlow()
    }

    parsePacketOrNullFunc =
      parsePacketOrNullFunc
        .beginControlFlow("else ->")
        .addStatement("""null""")
        .endControlFlow()

    parsePacketOrNullFunc.endControlFlow()

    val parsePacketFunc =
      FunSpec
        .builder(parsePacketFN)
        .addParameter("methodName", String::class.asTypeName())
        .addParameter("data", ByteString::class.asTypeName())
        .returns(
          // Message<*, Nothing>
          Message::class.asClassName().parameterizedBy(STAR, NOTHING),
        ).addCode(
          """
          when (val result = $parsePacketOrNullFN(methodName, data)) {
            null -> throw IllegalStateException("Unknown method ${DOLLAR}methodName")
            else -> return result
          }
          """.trimIndent(),
        )
    return listOf(parsePacketOrNullFunc.build(), parsePacketFunc.build())
  }
}
