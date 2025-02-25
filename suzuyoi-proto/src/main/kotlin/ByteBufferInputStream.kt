/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
package moe.sdl.suzuyoi.protos

import java.io.IOException
import java.io.InputStream
import java.nio.ByteBuffer

class ByteBufferBackedInputStream(
  private var buf: ByteBuffer,
) : InputStream() {
  @Throws(IOException::class)
  override fun read(): Int {
    if (!buf.hasRemaining()) {
      return -1
    }
    return buf.get().toInt() and 0xFF
  }

  @Throws(IOException::class)
  override fun read(
    bytes: ByteArray,
    off: Int,
    len: Int,
  ): Int {
    if (!buf.hasRemaining()) {
      return -1
    }

    val coercedLen = len.coerceAtMost(buf.remaining())
    buf.get(bytes, off, coercedLen)
    return coercedLen
  }
}

fun ByteBuffer.asInputStream(): ByteBufferBackedInputStream = ByteBufferBackedInputStream(this)
