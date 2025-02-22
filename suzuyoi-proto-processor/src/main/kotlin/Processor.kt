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
import com.squareup.kotlinpoet.FileSpec
import com.squareup.kotlinpoet.FunSpec
import com.squareup.kotlinpoet.KModifier
import com.squareup.kotlinpoet.asTypeName
import com.squareup.kotlinpoet.ksp.toClassName
import com.squareup.kotlinpoet.ksp.writeTo
import com.squareup.wire.WireRpc
import okio.ByteString
import java.io.OutputStream

private const val DOLLAR = "$"
private val WIRE_RPC_CLASS_NAME = WireRpc::class.qualifiedName!!

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
    var serverClz: KSClassDeclaration? = null
    val markedWireRpcFunctions =
      resolver
        .getSymbolsWithAnnotation(WIRE_RPC_CLASS_NAME)
        .filterIsInstance<KSFunctionDeclaration>()
        .filter {
          val clz = (it.parentDeclaration as? KSClassDeclaration) ?: return@filter false
          serverClz = clz
          clz.simpleName.getShortName() == "LobbyServer"
        }

    if (markedWireRpcFunctions.none()) return emptyList()

    if (serverClz == null) throw IllegalStateException("")

    val usedKSFile = mutableSetOf<KSFile>()

    val wireRpcAnnotations =
      markedWireRpcFunctions.map { func ->
        func.containingFile?.also { usedKSFile += it }
        val anno = func.annotations.first { it.shortName.getShortName() == "WireRpc" }
        val args =
          anno.arguments
            .map { arg ->
              val name = arg.name!!.getShortName()
              val value =
                arg.value as? String ?: throw IllegalStateException("$name is not an instance of String: ${anno.location}")
              name to value
            }.toMap()

        assert(args.containsKey("path")) { "The `@WireRpc` annotation does not contains `path` argument: ${anno.location}" }
        assert(
          args.containsKey("requestAdapter"),
        ) { "The `@WireRpc` annotation does not contains `requestAdapter` argument ${anno.location}" }
        assert(
          args.containsKey("responseAdapter"),
        ) { "The `@WireRpc` annotation does not contains `responseAdapter` argument ${anno.location}" }
        WireRpcData(
          args["path"]!!,
          args["requestAdapter"]!!,
          args["responseAdapter"]!!,
          func.simpleName.getShortName(),
        )
      }

    var funcSpec =
      FunSpec
        .builder("handleLobbyServerPacket")
        .addModifiers(KModifier.SUSPEND)
        .addParameter("server", serverClz!!.toClassName())
        .addParameter("methodName", String::class.asTypeName())
        .addParameter("data", ByteString::class.asTypeName())
        .returns(ByteString::class.asTypeName())
        .beginControlFlow("return when (methodName)")

    wireRpcAnnotations.forEach {
      funcSpec =
        funcSpec.beginControlFlow(
          """
          "${it.path.replace("/", ".")}" ->
          """.trimIndent(),
        )
      val requestAdapter = it.requestAdapter.replace("#", ".")
      funcSpec =
        funcSpec.addStatement(
          """
          server.${it.methodName}($requestAdapter.decode(data)).encodeByteString()
          """.trimIndent(),
        )
      funcSpec.endControlFlow()
    }

    funcSpec.beginControlFlow("else ->")
    funcSpec.addStatement("""throw IllegalStateException("Unknown method ${DOLLAR}methodName")""")
    funcSpec.endControlFlow()

    funcSpec.endControlFlow()

    val fileSpec =
      FileSpec
        .builder("moe.sdl.suzuyoi.protos", "MethodMap")
        .addKotlinDefaultImports(includeJs = false)
        .addFunction(
          funcSpec.build(),
        ).build()

    fileSpec.writeTo(codeGenerator, Dependencies(true, *usedKSFile.toTypedArray()))

    return emptyList()
  }
}
