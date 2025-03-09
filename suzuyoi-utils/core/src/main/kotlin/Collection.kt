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

import java.util.concurrent.ConcurrentHashMap

inline fun <reified K, reified V> buildConcurrencyMap(
  initCapacity: Int = 16,
  builder: ConcurrentHashMap<K, V>.() -> Unit,
): ConcurrentHashMap<K, V> {
  val map = ConcurrentHashMap<K, V>(initCapacity)
  map.builder()
  return map
}
