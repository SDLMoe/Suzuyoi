/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
package moe.sdl.suzuyoi.utils

import java.nio.file.Path
import kotlin.io.path.exists
import kotlin.io.path.pathString

/**
 * Global Work Directory, set by `user.dir`
 */
val globalWorkDirectory: Path by lazy {
  val cwd = System.getProperty("user.dir") ?: error("Failed to get property 'user.dir'")
  val workspace = Path.of(cwd, "_workspace")
  val new =
    if (workspace.exists()) {
      workspace
    } else {
      Path.of(cwd)
    }
  System.setProperty("user.dir", new.pathString)
  logger.atDebug().log { "Global work directory: $cwd" }
  new
}

val configDirectory: Path by lazy { globalWorkDirectory.resolve("config") }
