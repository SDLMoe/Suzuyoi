/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
@file:JvmName("Suzuyoi")

package moe.sdl.suzuyoi

import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import moe.sdl.suzuyoi.protos.handleLobbyServerPacket
import kotlin.time.Duration.Companion.seconds

fun main(): Unit =
  runBlocking {
    embeddedServer(Netty, port = 8080) {
      install(WebSockets) {
        pingPeriod = 15.seconds
        timeout = 15.seconds
        maxFrameSize = Long.MAX_VALUE
        masking = false
      }
      routing {
        webSocket("/gateway") {
          val packetContext = Packet.Context()
          for (frame in incoming) {
            when (frame) {
              is Frame.Binary -> {
                launch {
                  val pkt = Packet.parse(frame.data.inputStream().buffered())
                  when (pkt) {
                    is Request -> handleLobbyServerPacket(LobbyServerImpl, pkt.methodName, pkt.data)
                    is Notify -> throw IllegalStateException("Server should not receive notify packet")
                    is Response -> throw IllegalStateException("Server should not receive response packet")
                  }
                }
              }
              else -> {}
            }
          }
        }
      }
    }.start(wait = true)
  }
