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

import moe.sdl.suzuyoi.protos.BaseMessage
import okio.ByteString
import okio.ByteString.Companion.readByteString
import java.io.InputStream
import java.nio.ByteBuffer
import java.util.concurrent.ConcurrentLinkedDeque

sealed class PacketDecodeException(
  message: String,
  cause: Throwable? = null,
) : IllegalStateException(message, cause) {
  class InvalidEof : PacketDecodeException("Invalid EOF")

  class InvalidMessageType(
    raw: Int,
  ) : PacketDecodeException("Invalid message type: $raw")

  class MissingRequestPacket(
    code: UShort,
  ) : PacketDecodeException("No matching request packet for client, code: $code")

  class Others(
    cause: Throwable,
  ) : PacketDecodeException("Unknown error", cause)
}

data class NameEntry(
  val code: UShort,
  val methodName: String,
)

sealed class Packet {
  abstract val methodName: String
  abstract val data: ByteString

  class Context(
    val deque: ConcurrentLinkedDeque<NameEntry>,
    val len: Int,
  ) {
    constructor() : this(ConcurrentLinkedDeque(), 20)

    override fun toString(): String = "PacketContext(deque=$deque, len=$len)"
  }

  companion object {
    @JvmStatic
    @Throws(PacketDecodeException::class)
    fun parse(
      stream: InputStream,
      ctx: Context? = null,
    ): Packet =
      runCatching {
        when (val type = stream.read()) {
          -1 -> {
            throw PacketDecodeException.InvalidEof()
          }
          // notify: server -> client actively
          1 -> {
            val base = BaseMessage.ADAPTER.decode(stream)
            Notify(base.method_name, base.data_)
          }
          // request: client -> server
          2 -> {
            val code = ByteBuffer.wrap(stream.readNBytes(2)).getShort().toUShort()
            val base = BaseMessage.ADAPTER.decode(stream)
            if (ctx != null) {
              ctx.deque.offerLast(NameEntry(code, base.method_name))
              while (ctx.deque.size > ctx.len) ctx.deque.removeFirst()
            }
            Request(code, base.method_name, base.data_)
          }
          // response: server -> client passively
          3 -> {
            val code = ByteBuffer.wrap(stream.readNBytes(2)).getShort().toUShort()
            if (ctx?.deque == null) throw PacketDecodeException.MissingRequestPacket(code)
            val entry =
              ctx.deque
                .reversed()
                .firstOrNull { it.code == code }
                ?: throw PacketDecodeException.MissingRequestPacket(code)
            val data = stream.readByteString(stream.available())
            Response(code, entry.methodName, data)
          }

          else -> throw PacketDecodeException.InvalidMessageType(type)
        }
      }.onFailure {
        if (it !is PacketDecodeException) throw PacketDecodeException.Others(it)
      }.getOrThrow()

    @JvmStatic
    fun parseOrNull(
      stream: InputStream,
      ctx: Context? = null,
    ): Packet? =
      runCatching {
        parse(stream, ctx)
      }.getOrNull()
  }
}

/**
 * server -> client actively
 */
data class Notify(
  override val methodName: String,
  override val data: ByteString,
) : Packet()

/**
 * client -> server
 */
data class Request(
  val code: UShort,
  override val methodName: String,
  override val data: ByteString,
) : Packet()

/**
 * server -> client passively
 */
data class Response(
  val code: UShort,
  override val methodName: String,
  override val data: ByteString,
) : Packet()
