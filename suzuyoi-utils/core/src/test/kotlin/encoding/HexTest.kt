/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
package moe.sdl.suzuyoi.utils.encoding

import kotlin.test.Test
import kotlin.test.assertEquals

class HexTest {
  @Test
  fun hexEncode() =
    assertEquals(
      "f09f9882e39c89e381b2e38289e3818ce381aa6173666a61646c6b2626262128383233",
      "😂㜉ひらがなasfjadlk&&&!(823".toByteArray().hex,
    )
}
