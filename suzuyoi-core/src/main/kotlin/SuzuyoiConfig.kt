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

import com.charleskorn.kaml.YamlComment
import kotlinx.serialization.Serializable
import moe.sdl.suzuyoi.data.provider.DataFilePersist
import moe.sdl.suzuyoi.utils.*

val suzuyoiConfigPath = configDirectory.resolve("config.yaml")
val suzuyoiConfig =
  DataFilePersist<SuzuyoiConfig>(
    suzuyoiConfigPath.toFile(),
    format = lenientYaml,
  ) {
    SuzuyoiConfig()
  }

@Serializable
data class SuzuyoiConfig(
  val server: ServerConfig = ServerConfig(),
)

@Serializable
data class ServerConfig(
  @YamlComment("Bind address of dispach server")
  val host: String = "0.0.0.0",
  @YamlComment("Bind port of dispatch server")
  val port: Int = 443,
  @YamlComment(
    "Use SSL encrypted connection",
    "If you use fiddler, mitmdump, or some similar tools to forward your connection,",
    "and decrypted the forwarding traffic, you could turn off this option.",
  )
  val useSSL: Boolean = true,
  @YamlComment("SSL certification setting")
  var certification: Certification = Certification(),
)

@Serializable
data class Certification(
  @YamlComment("The path of ssl private key file, relative to the directory containing this config.")
  private val keyStorePath: String =
    configDirectory
      .resolve("suzuyoi-cert" + if (isWindows) ".pfx" else ".jks")
      .toFile()
      .toMaybeChildPathString(suzuyoiConfigPath.parent.toFile()),
  @YamlComment("The path of ssl certification, relative to the directory containing this config.")
  private val certPath: String =
    configDirectory
      .resolve("suzuyoi-cert.cert")
      .toFile()
      .toMaybeChildPathString(suzuyoiConfigPath.parent.toFile()),
  val keyStoreType: String = if (isWindows) "PKCS12" else "JKS",
  val keyAlias: String = "suzuyoi-dispatch-cert",
  val keyStorePassword: String = "suzuyoi-dispatch",
  val privateKeyPassword: String = "suzuyoi-dispatch-private",
  val expiredDays: Long = 365 * 10L,
) {
  fun keyStorePath() = suzuyoiConfigPath.parent.resolve(keyStorePath)

  fun certPath() = suzuyoiConfigPath.parent.resolve(certPath)
}
