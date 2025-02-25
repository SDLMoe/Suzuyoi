/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
package moe.sdl.suzuyoi

import moe.sdl.suzuyoi.protos.*
import okio.buffer
import okio.sink
import okio.source
import java.io.File
import java.util.concurrent.ConcurrentLinkedDeque
import kotlin.io.path.Path
import kotlin.io.path.exists

fun main() {
  val root =
    System
      .getenv("RECORDS_PATH")
      ?.also { require(Path(it).exists()) }
      ?.let { File(it) }
      ?: File("/Users/col/Developer/majsoul-pro/records")
  println("Scanning root = `${root.absolutePath}`")
  val ctx = Packet.Context(ConcurrentLinkedDeque(), 50)
  var i = 0
  val failureTypes = hashMapOf<String, Int>()
  val failureTypesLen = hashMapOf<String, MutableList<Int>>()
  root
    .walk()
    .maxDepth(1)
    .asSequence()
    .filter { it.isFile }
    .filter { it.extension == "bin" }
    .sortedBy {
      val ts =
        it.name.split('-', limit = 2).getOrNull(0)
          ?: throw IllegalStateException("Invalid filename ${it.name}")
      ts.toLongOrNull() ?: throw IllegalStateException("Invalid filename ${it.name}")
    }.forEach {
      runCatching {
        when (val pkt = Packet.parse(it.inputStream(), ctx)) {
          is Notify -> {
            when (pkt.methodName) {
              ".lq.ActionPrototype" -> {
                val action =
                  ActionPrototype.ADAPTER.decode(
                    pkt.data
                      .toByteArray()
                      .inputStream()
                      .source()
                      .buffer(),
                  )
                val actions =
                  runCatching {
                    File("records").mkdirs()
                    File("records/${++i}-${action.name}.bin")
                      .sink()
                      .buffer()
                      .use { input ->
                        input.write(action.data_)
                      }

                    Actions.parseOrNull(action)
                  }.onFailure {
                    val last = failureTypes.getOrDefault(action.name, 0)
                    failureTypes[action.name] = last + 1
                    val lastLen = failureTypesLen.getOrPut(action.name) { mutableListOf() }
                    lastLen.add(action.data_.size)
                  }.getOrThrow()

                println(
                  "Notify .lq.ActionPrototype $actions",
                )
              }

              else -> println("Notify ${pkt.methodName}: not implemented")
            }
          }

          is Request -> {
            val msg =
              parseLobbyServerRequestPacketOrNull(pkt.methodName, pkt.data)
                ?: parseFastTestServerRequestPacketOrNull(pkt.methodName, pkt.data)
            println(
              "code=${pkt.code} Req ${pkt.methodName}: $msg",
            )
          }

          is Response -> {
            val msg =
              parseLobbyServerResponsePacketOrNull(pkt.methodName, pkt.data)
                ?: parseFastTestServerResponsePacketOrNull(pkt.methodName, pkt.data)
            println(
              "code=${pkt.code} Resp ${pkt.methodName}: $msg",
            )
          }
        }
      }.onFailure {
        println("$it")
      }
    }
  println(failureTypes.toList().sortedByDescending { it.second }.joinToString("\n") { (k, v) -> "$k -> $v" })
  println(failureTypesLen.toList().joinToString("\n") { (k, v) -> "$k -> ${v.joinToString()}" })
}
