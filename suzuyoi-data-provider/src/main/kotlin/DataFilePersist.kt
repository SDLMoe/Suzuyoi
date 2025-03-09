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

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import moe.sdl.commons.coroutines.ModuleScope
import moe.sdl.suzuyoi.utils.*
import java.io.File

inline fun <reified T : Any> DataFilePersist(
  file: File,
  format: StringFormat = prettyJson,
  scope: CoroutineScope = ModuleScope("DataFilePersist", dispatcher = Dispatchers.IO),
  noinline defaultBuilder: () -> T? = { null },
) = DataFilePersist(file, serializer(), format, scope, defaultBuilder)

/**
 * Data file persist
 * @property file where you store the data
 * @param default the default value
 * @see FilePersist
 */
@Suppress("MemberVisibilityCanBePrivate")
open class DataFilePersist<T : Any>(
  protected val file: File,
  protected val serializer: KSerializer<T>,
  protected val format: StringFormat = prettyJson,
  protected val scope: CoroutineScope =
    ModuleScope("DataFilePersist", dispatcher = Dispatchers.IO),
  protected val defaultBuilder: () -> T? = { null },
) : FilePersist<T>,
  CoroutineScope by scope {
  protected val mutex = Mutex()

  @Volatile
  private var _data: T? = null

  override val data: T
    get() = _data ?: error("Data file not loaded, file=${file.toMaybeChildPathToCwd()}, format=$format")

  suspend fun initAndLoad(): T =
    withContext(scope.coroutineContext) {
      init()
      load()
    }

  override suspend fun save(saveData: T): Unit =
    mutex.withLock {
      withContext(scope.coroutineContext) {
        logger.atDebug().log {
          "Saving data: ${saveData::class.qualifiedName}"
        }
        file.touch()
        file.writeTextBuffered(format.encodeToString(serializer, saveData))
      }
    }

  override suspend fun load(): T =
    withContext(scope.coroutineContext) {
      if (!file.exists()) {
        logger.atDebug().log { " ${file.toMaybeChildPathToCwd()} does not exist, creating new default config..." }
        save(defaultBuilder() ?: error("Config file ${file.path} does not exist."))
      }
      mutex.withLock {
        val json = file.readTextBuffered()
        val t = format.decodeFromString(serializer, json)
        _data = t
        t.also {
          logger.atDebug().log { "Loaded data: ${it::class.qualifiedName}" }
          logger.atTrace().log { "Data content $it" }
        }
      }
    }
}
