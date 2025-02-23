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

import moe.sdl.suzuyoi.protos.parseLobbyServerRequestPacket
import moe.sdl.suzuyoi.protos.parseLobbyServerResponsePacket
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
      ?: File("records")
  println("Scanning root = `${root.absolutePath}`")
  val ctx = Packet.Context(ConcurrentLinkedDeque(), 50)
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
      when (val rawPkt = Packet.parse(it.inputStream(), ctx)) {
        is Notify -> {
          println("Notify ${rawPkt.methodName}: not implemented")
        }

        is Request -> {
          println(
            "code=${rawPkt.code} Req ${rawPkt.methodName}: ${
              parseLobbyServerRequestPacket(
                rawPkt.methodName,
                rawPkt.data,
              )
            }",
          )
        }

        is Response -> {
          println(
            "code=${rawPkt.code} Resp ${rawPkt.methodName}: ${
              parseLobbyServerResponsePacket(
                rawPkt.methodName,
                rawPkt.data,
              )
            }",
          )
        }
      }
    }
}
