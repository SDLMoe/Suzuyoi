/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
package moe.sdl.suzuyoi.data.provider

/**
 * Local file data persist storage
 * @property file where you store the data
 * @property scope coroutine will launch from this scope,
 * keep it same as caller side to make sure the structured concurrency has been followed
 */
interface FilePersist<T : Any> {
  val data: T

  /**
   * Initialize file persist
   * Must be called at program start
   */
  suspend fun init() = Unit

  /**
   * Load data from disk storage,
   * assign value to [data], then return it
   */
  suspend fun load(): T

  /**
   * Save data to disk
   *
   * Should be invoked after change, or data will be lost
   */
  suspend fun save(saveData: T = data)

  /**
   * Update data and save it to disk
   */
  suspend fun update(action: (T) -> T) {
    save(action(data))
  }
}
