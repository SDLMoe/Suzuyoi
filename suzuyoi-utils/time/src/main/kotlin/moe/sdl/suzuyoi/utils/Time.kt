/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
@file:Suppress("NOTHING_TO_INLINE")

package moe.sdl.suzuyoi.utils

import kotlinx.datetime.*
import java.time.ZonedDateTime

/**
 * Just a fast path for [Clock.System.now]
 */
inline fun now() = Clock.System.now()

/**
 * Convert KotlinX [Instant] to Java [ZonedDateTime]
 */
fun Instant.toZonedUtc(): ZonedDateTime = toJavaInstant().atZone(TimeZone.UTC.toJavaZoneId())

inline fun nowSeconds() = now().epochSeconds

inline fun nowMilliseconds() = now().toEpochMilliseconds()

inline fun Long.millisToInstant() = Instant.fromEpochMilliseconds(this)

inline fun Long.secondToInstant() = Instant.fromEpochSeconds(this)
