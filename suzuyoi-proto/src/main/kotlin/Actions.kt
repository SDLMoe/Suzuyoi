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

import com.squareup.wire.ProtoReader
import okio.buffer
import okio.source

sealed interface Actions {
  val step: Int

  companion object {
    fun decode(protobuf: ByteArray): ByteArray {
      val g = ubyteArrayOf(132u, 94u, 78u, 66u, 57u, 162u, 31u, 96u, 28u)
      val newProtobuf = ByteArray(protobuf.size)
      for (i in protobuf.indices) {
        val key = ((23 xor protobuf.size) + 5 * i + g[i % g.size].toInt()) and 255
        newProtobuf[i] = (protobuf[i].toInt() xor key).toByte()
      }
      return newProtobuf
    }

    @JvmStatic
    fun parseOrNull(action: ActionPrototype): Actions? = parseOrNull(action.name, action.step, action.data_.toByteArray())

    @JvmStatic
    fun parseOrNull(
      actionName: String,
      step: Int,
      data: ByteArray,
    ): Actions? =
      runCatching {
        val source = decode(data).inputStream().source().buffer()
        when (actionName) {
          "ActionAnGangAddGang" -> AnGangAddGang(step, ActionAnGangAddGang.ADAPTER.decode(ProtoReader(source)))
          "ActionBaBei" -> BaBei(step, ActionBaBei.ADAPTER.decode(ProtoReader(source)))
          "ActionChangeTile" -> ChangeTile(step, ActionChangeTile.ADAPTER.decode(ProtoReader(source)))
          "ActionChiPengGang" -> ChiPengGang(step, ActionChiPengGang.ADAPTER.decode(ProtoReader(source)))
          "ActionDealTile" -> DealTile(step, ActionDealTile.ADAPTER.decode(ProtoReader(source)))
          "ActionDiscardTile" -> DiscardTile(step, ActionDiscardTile.ADAPTER.decode(ProtoReader(source)))
          "ActionFillAwaitingTiles" -> FillAwaitingTiles(step, ActionFillAwaitingTiles.ADAPTER.decode(ProtoReader(source)))
          "ActionGangResult" -> GangResult(step, ActionGangResult.ADAPTER.decode(ProtoReader(source)))
          "ActionGangResultEnd" -> GangResultEnd(step, ActionGangResultEnd.ADAPTER.decode(ProtoReader(source)))
          "ActionHule" -> Hule(step, ActionHule.ADAPTER.decode(ProtoReader(source)))
          "ActionHuleXueZhanEnd" -> HuleXueZhanEnd(step, ActionHuleXueZhanEnd.ADAPTER.decode(ProtoReader(source)))
          "ActionHuleXueZhanMid" -> HuleXueZhanMid(step, ActionHuleXueZhanMid.ADAPTER.decode(ProtoReader(source)))
          "ActionLiuJu" -> LiuJu(step, ActionLiuJu.ADAPTER.decode(ProtoReader(source)))
          "ActionLockTile" -> LockTile(step, ActionLockTile.ADAPTER.decode(ProtoReader(source)))
          "ActionMJStart" -> MJStart(step, ActionMJStart.ADAPTER.decode(ProtoReader(source)))
          "ActionNewCard" -> NewCard(step, ActionNewCard.ADAPTER.decode(ProtoReader(source)))
          "ActionNewRound" -> NewRound(step, ActionNewRound.ADAPTER.decode(ProtoReader(source)))
          "ActionNoTile" -> NoTile(step, ActionNoTile.ADAPTER.decode(ProtoReader(source)))
          "ActionPrototype" -> Prototype(step, ActionPrototype.ADAPTER.decode(ProtoReader(source)))
          "ActionRevealTile" -> RevealTile(step, ActionRevealTile.ADAPTER.decode(ProtoReader(source)))
          "ActionSelectGap" -> SelectGap(step, ActionSelectGap.ADAPTER.decode(ProtoReader(source)))
          "ActionUnveilTile" -> UnveilTile(step, ActionUnveilTile.ADAPTER.decode(ProtoReader(source)))
          else -> {
            println("Unknown action: $actionName")
            null
          }
        }
      }.onFailure { err ->
        throw RuntimeException("ActionDecodeError $actionName", err)
      }.getOrNull()
  }

  data class AnGangAddGang(
    override val step: Int,
    val inner: ActionAnGangAddGang,
  ) : Actions

  data class BaBei(
    override val step: Int,
    val inner: ActionBaBei,
  ) : Actions

  data class ChangeTile(
    override val step: Int,
    val inner: ActionChangeTile,
  ) : Actions

  data class ChiPengGang(
    override val step: Int,
    val inner: ActionChiPengGang,
  ) : Actions

  data class DealTile(
    override val step: Int,
    val inner: ActionDealTile,
  ) : Actions

  data class DiscardTile(
    override val step: Int,
    val inner: ActionDiscardTile,
  ) : Actions

  data class FillAwaitingTiles(
    override val step: Int,
    val inner: ActionFillAwaitingTiles,
  ) : Actions

  data class GangResult(
    override val step: Int,
    val inner: ActionGangResult,
  ) : Actions

  data class GangResultEnd(
    override val step: Int,
    val inner: ActionGangResultEnd,
  ) : Actions

  data class Hule(
    override val step: Int,
    val inner: ActionHule,
  ) : Actions

  data class HuleXueZhanEnd(
    override val step: Int,
    val inner: ActionHuleXueZhanEnd,
  ) : Actions

  data class HuleXueZhanMid(
    override val step: Int,
    val inner: ActionHuleXueZhanMid,
  ) : Actions

  data class LiuJu(
    override val step: Int,
    val inner: ActionLiuJu,
  ) : Actions

  data class LockTile(
    override val step: Int,
    val inner: ActionLockTile,
  ) : Actions

  data class MJStart(
    override val step: Int,
    val inner: ActionMJStart,
  ) : Actions

  data class NewCard(
    override val step: Int,
    val inner: ActionNewCard,
  ) : Actions

  data class NewRound(
    override val step: Int,
    val inner: ActionNewRound,
  ) : Actions

  data class NoTile(
    override val step: Int,
    val inner: ActionNoTile,
  ) : Actions

  data class Prototype(
    override val step: Int,
    val inner: ActionPrototype,
  ) : Actions

  data class RevealTile(
    override val step: Int,
    val inner: ActionRevealTile,
  ) : Actions

  data class SelectGap(
    override val step: Int,
    val inner: ActionSelectGap,
  ) : Actions

  data class UnveilTile(
    override val step: Int,
    val inner: ActionUnveilTile,
  ) : Actions
}
