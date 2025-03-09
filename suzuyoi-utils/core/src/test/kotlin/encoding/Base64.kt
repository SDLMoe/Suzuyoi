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
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class Base64 {
  // Basic Test

  @Test
  fun `string encode to string`() {
    val orig = "\uD83D\uDE02\uD855\uDDB6ひらがなa...a..a.a.**@&@#*#@*!@//::>>"
    assertEquals(
      "8J+YgvCllrbjgbLjgonjgYzjgaphLi4uYS4uYS5hLioqQCZAIyojQCohQC8vOjo+Pg==",
      orig.encodeBase64(),
    )
  }

  @Test
  fun `string decode to string`() {
    val orig = "8J+YgvCllrbjgbLjgonjgYzjgaphLi4uYS4uYS5hLioqQCZAIyojQCohQC8vOjo+Pg=="
    assertEquals(
      "\uD83D\uDE02\uD855\uDDB6ひらがなa...a..a.a.**@&@#*#@*!@//::>>",
      orig.decodeBase64ToString(),
    )
  }

  @Test
  fun `byte array encode to string`() {
    val orig = byteArrayOf(10, 20, 30, 50, 60, 123, 78, -100, -50)
    assertEquals(
      "ChQeMjx7TpzO",
      orig.encodeBase64(),
    )
  }

  @Test
  fun `string decode to byte array`() {
    val orig = "ChQeMjx7TpzO"
    assertContentEquals(
      byteArrayOf(10, 20, 30, 50, 60, 123, 78, -100, -50),
      orig.decodeBase64(),
    )
  }

  // Url Safe Tests

  @Test
  fun `string encode to string url safe`() {
    val orig = "\uD83D\uDE02\uD855\uDDB6ひらがなa...a..a.a.**@&@#*#@*!@//::>>"
    assertEquals(
      "8J-YgvCllrbjgbLjgonjgYzjgaphLi4uYS4uYS5hLioqQCZAIyojQCohQC8vOjo-Pg==",
      orig.encodeBase64(Base64Impl.UrlSafe),
    )
  }

  @Test
  fun `string decode to string url safe`() {
    val orig = "8J-YgvCllrbjgbLjgonjgYzjgaphLi4uYS4uYS5hLioqQCZAIyojQCohQC8vOjo-Pg=="
    assertEquals(
      "\uD83D\uDE02\uD855\uDDB6ひらがなa...a..a.a.**@&@#*#@*!@//::>>",
      orig.decodeBase64ToString(Base64Impl.UrlSafe),
    )
  }
}
