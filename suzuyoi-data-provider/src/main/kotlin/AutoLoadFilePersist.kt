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

import io.github.irgaly.kfswatch.KfsDirectoryWatcher
import io.github.irgaly.kfswatch.KfsDirectoryWatcherEvent
import kotlinx.coroutines.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.StringFormat
import kotlinx.serialization.serializer
import moe.sdl.commons.coroutines.ModuleScope
import moe.sdl.suzuyoi.utils.prettyJson
import java.io.File

inline fun <reified T : Any> AutoLoadFilePersist(
  file: File,
  format: StringFormat = prettyJson,
  scope: ModuleScope = ModuleScope("DataFilePersist", dispatcher = Dispatchers.IO),
  noinline defaultBuilder: () -> T? = { null },
) = AutoLoadFilePersist(file, serializer(), format, scope, defaultBuilder)

/**
 * Auto Load File Persist
 * @see [DataFilePersist]
 * @see [FilePersist]
 */
open class AutoLoadFilePersist<T : Any>(
  file: File,
  serializer: KSerializer<T>,
  format: StringFormat = prettyJson,
  scope: ModuleScope = ModuleScope("AutoLoadFilePersist", dispatcher = Dispatchers.IO),
  defaultBuilder: () -> T? = { null },
) : DataFilePersist<T>(file, serializer, format, scope, defaultBuilder) {
  override suspend fun init() {
    super.init()
    launchScanJob()
  }

  private val watcher by lazy {
    KfsDirectoryWatcher(scope, dispatcher = Dispatchers.IO)
  }

  private val scanJob: Job by lazy {
    scope.launch {
      watcher.add(file.parent)
      val onModifiedScope = scope.subscope("onModified")
      watcher.onEventFlow.collect { event ->
        onModifiedScope.launch {
          if (event.targetDirectory == file.parent && file.name == event.path) {
            onModified(event)
          }
        }
      }
      watcher.remove(file.parent)
    }
  }

  open suspend fun onModified(event: KfsDirectoryWatcherEvent) {
    load()
  }

  private fun launchScanJob() = scanJob
}
