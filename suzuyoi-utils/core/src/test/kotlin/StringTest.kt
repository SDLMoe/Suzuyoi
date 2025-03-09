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

import org.junit.experimental.runners.Enclosed
import org.junit.runner.RunWith
import kotlin.test.Test
import kotlin.test.assertEquals

@RunWith(Enclosed::class)
class StringTest {
  class ReplaceWithOrder {
    @Test
    fun `index start from 0`() {
      assertEquals("0 1 2", "{0} {1} {2}".replaceWithOrder("0", "1", "2"))
    }

    @Test
    fun `order matters`() {
      assertEquals("0 1", "{0} {1}".replaceWithOrder("0", "1"))
      assertEquals("1 0", "{1} {0}".replaceWithOrder("0", "1"))
    }

    @Test
    fun `ignore extra args`() {
      assertEquals("0", "{0}".replaceWithOrder("0", "1"))
    }

    @Test
    fun `ignore extra placeholder`() {
      assertEquals("0 {1}", "{0} {1}".replaceWithOrder("0"))
    }
  }
}
