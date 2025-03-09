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

import io.ktor.network.tls.certificates.*
import io.ktor.network.tls.extensions.*
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import io.ktor.server.websocket.*
import io.ktor.util.*
import io.ktor.websocket.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import moe.sdl.suzuyoi.protos.handleLobbyServerRequest
import org.slf4j.LoggerFactory
import java.io.File
import java.security.KeyStore
import javax.security.auth.x500.X500Principal
import kotlin.time.Duration.Companion.seconds

internal fun KeyStore.saveCertToFile(
  output: File,
  alias: String,
) {
  output.parentFile?.mkdirs()
  val certBegin = "-----BEGIN CERTIFICATE-----\n"
  val endCert = "-----END CERTIFICATE-----"
  output.outputStream().use {
    it.write(certBegin.toByteArray())
    it.write(getCertificate(alias).encoded.encodeBase64().toByteArray())
    it.write(endCert.toByteArray())
  }
}

fun main(): Unit =
  runBlocking {
    val config = suzuyoiConfig.initAndLoad()
    val certConfig = config.server.certification
    val keystoreFile = certConfig.keyStorePath().toFile()
    val keystore =
      if (!keystoreFile.exists()) {
        val generatedKeyStore =
          buildKeyStore {
            certificate(certConfig.keyAlias) {
              hash = HashAlgorithm.SHA256
              sign = SignatureAlgorithm.RSA
              keySizeInBits = 2048
              password = certConfig.privateKeyPassword
              daysValid = certConfig.expiredDays
              domains = listOf("localhost")
              this.subject = X500Principal("CN=Suzuyoi MITM, OU=Suzuyoi, O=Suzuyoi, C=US")
              keyType = KeyType.Server
            }
          }
        generatedKeyStore.saveToFile(keystoreFile, certConfig.keyStorePassword)
        generatedKeyStore.saveCertToFile(certConfig.certPath().toFile(), certConfig.keyAlias)
        generatedKeyStore
      } else {
        val keystore = KeyStore.getInstance(certConfig.keyStoreType)
        keystore.load(keystoreFile.inputStream().buffered(), certConfig.keyStorePassword.toCharArray())
        keystore
      }

    val env =
      applicationEnvironment {
        log = LoggerFactory.getLogger("ktor.application")
      }
    embeddedServer(factory = Netty, environment = env, configure = {
      if (!config.server.useSSL) return@embeddedServer
      sslConnector(
        keystore,
        certConfig.keyAlias,
        { certConfig.keyStorePassword.toCharArray() },
        { certConfig.privateKeyPassword.toCharArray() },
      ) {
        port = 1145
      }
    }) {
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
                  when (val pkt = Packet.parse(frame.data.inputStream().buffered())) {
                    is Request -> handleLobbyServerRequest(LobbyServerImpl, pkt.methodName, pkt.data)
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
