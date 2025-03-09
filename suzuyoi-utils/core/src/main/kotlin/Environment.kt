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

val isWindows: Boolean by lazy {
  osType == OsType.WINDOWS
}

val isMac: Boolean by lazy {
  osType == OsType.MAC
}

val isLinux: Boolean by lazy {
  osType == OsType.LINUX
}

val osType: OsType by lazy {
  System.getProperty("os.name")?.lowercase()?.run {
    when {
      contains("win") -> OsType.WINDOWS
      listOf("nix", "nux", "aix").any { contains(it) } -> OsType.LINUX
      contains("mac") -> OsType.MAC
      contains("sunos") -> OsType.SOLARIS
      else -> OsType.OTHER
    }
  } ?: OsType.OTHER
}

enum class OsType { WINDOWS, LINUX, MAC, SOLARIS, OTHER }
