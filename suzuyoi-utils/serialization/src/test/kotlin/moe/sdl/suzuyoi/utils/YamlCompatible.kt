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

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlin.test.Test

class YamlCompatible {
  @Test
  fun `additional field must be okay with filled default`() {
    @Serializable
    data class Field1(
      val xxxxx: String = "123123",
    )

    @Serializable
    data class Field2(
      val xxxxx: String = "123123",
      val yyyyy: String = "123123",
    )

    Yaml(configuration = YamlConfiguration())

    val before = lenientYaml.encodeToString(Field1.serializer(), Field1())

    println(before)

    val after = lenientYaml.decodeFromString<Field2>(before)

    println(after)

    val reEncode = lenientYaml.encodeToString(Field2.serializer(), after)

    println(reEncode)
  }

  @Test
  fun `ignore unknown keys`() {
    @Serializable
    data class Field1(
      val xxxxx: String = "123123",
    )

    val input =
      """
      xxxxx: 123123
      yyyyy: 123123
      """.trimIndent()

    lenientYaml.decodeFromString(Field1.serializer(), input).also(::println)
  }
}
