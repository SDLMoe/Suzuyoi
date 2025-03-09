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

private val hexChars by lazy { "0123456789abcdef".toCharArray() }

/**
 * Encode byte array to hex
 */
@Suppress("MagicNumber")
val ByteArray.hex: String
  // Experimental Code, replace when stabled
  // get() = asUByteArray().joinToString("") { it.toString(radix = 16).padStart(2, '0') }
  get() {
    val hex = CharArray(2 * this.size)
    this.forEachIndexed { i, byte ->
      val unsigned = 0xff and byte.toInt()
      hex[2 * i] = hexChars[unsigned / 16]
      hex[2 * i + 1] = hexChars[unsigned % 16]
    }

    return hex.joinToString("")
  }
