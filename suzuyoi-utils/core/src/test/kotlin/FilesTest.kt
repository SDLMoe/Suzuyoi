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

import kotlin.test.Test

class FilesTest {
  @Test
  fun resolveGlobalWorkdir() {
    assert(globalWorkDirectory.also(::println).isDirectory)
  }

  @Test
  fun resolveSubWorkDir() {
    assert(resolveWorkDirectory("./config/newfile.txt").also(::println).name == "newfile.txt")
  }
}
