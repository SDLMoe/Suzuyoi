/**
 * Copyright 2025 the original author or authors.
 *
 * All rights reserved. This program and the accompanying materials are
 * made available under the terms of the MIT license which
 * accompanies this distribution and is available at
 *
 * https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE
 */
@file:Suppress("unused")

package moe.sdl.suzuyoi.utils

import java.io.BufferedReader
import java.io.File
import java.io.IOException
import java.io.InputStream
import java.net.URL
import java.util.jar.JarFile
import kotlin.io.path.absolute
import kotlin.io.path.pathString

/**
 * Create a file and its parents
 */
fun File.touch(): Boolean {
  parentFile?.mkdirs()
  return createNewFile()
}

/**
 * @return normalized absolute path of [File]
 */
val File.absPath
  get() = toPath().normalize().absolute().pathString

/**
 *
 */
fun File.toMaybeChildPathString(parent: File): String {
  val isChildOfCwd = canonicalPath.startsWith(parent.canonicalPath)
  return if (isChildOfCwd) {
    absoluteFile.toRelativeString(parent.absoluteFile)
  } else {
    absolutePath
  }
}

fun File.toMaybeChildPathToCwd(): String = this.toMaybeChildPathString(globalWorkDirectory.toFile())

/**
 * Read text from the file
 */
fun File.readTextBuffered() = inputStream().bufferedReader().use(BufferedReader::readText)

/**
 * Write text to the file
 */
fun File.writeTextBuffered(text: String) = outputStream().bufferedWriter().use { it.appendLine(text) }

/**
 * Writes an input stream to a file.
 * @param input The input stream
 * @param target The target file
 * @throws IOException If there is an I/O error
 */
fun writeToFile(
  input: InputStream,
  target: File,
): Unit =
  input.use {
    target.outputStream().use { output ->
      input.copyTo(output)
    }
  }

/**
 * Get resource of current class loader as [InputStream]
 */
fun getResource(path: String): URL? = contextClassLoader?.getResource(path)

/**
 * Get resource of current class loader as [InputStream]
 */
fun getResourceAsStream(path: String): InputStream? = contextClassLoader?.getResourceAsStream(path)

private const val JAR_URI_PREFIX = "jar:file:"

/**
 * Get resources of current class loader as URLs
 */
fun getResources(path: String): List<URL> =
  contextClassLoader
    ?.getResources(path)
    ?.toList()
    ?.filterNotNull()
    .orEmpty()

/**
 * Copies a set of resources into a temporal directory, optionally preserving
 * the paths of the resources.
 * @param preserve Whether the files should be placed directly in the
 * directory or the source path should be kept
 * @param paths The paths to the resources
 * @return The temporal directory
 * @throws IOException If there is an I/O error
 */
fun copyResourcesToTempDir(
  preserve: Boolean,
  vararg paths: String,
): File {
  val parent = File(System.getProperty("java.io.tmpdir"))
  var directory: File
  do {
    directory = File(parent, System.nanoTime().toString())
  } while (!directory.mkdir())
  return copyResourcesToDir(directory, preserve, *paths)
}

/**
 * Copies a set of resources into a directory, preserving the paths
 * and names of the resources.
 * @param directory The target directory
 * @param preserve Whether the files should be placed directly in the
 * directory or the source path should be kept
 * @param paths The paths to the resources
 * @return The temporal directory
 * @throws IOException If there is an I/O error
 */
fun copyResourcesToDir(
  directory: File,
  preserve: Boolean,
  vararg paths: String,
): File {
  paths.forEach {
    val input = getResourceAsStream(it) ?: error("Failed to get resource as stream")

    val target: File =
      if (preserve) {
        File(directory, it).apply {
          parentFile.mkdirs()
        }
      } else {
        File(directory, File(it).name)
      }

    writeToFile(input, target)
  }
  return directory
}

/**
 * Copies a resource directory from inside a JAR file to a target directory.
 * @param source The JAR file
 * @param path The path to the directory inside the JAR file
 * @param target The target directory
 * @throws IOException If there is an I/O error
 */
fun copyResourceDirectory(
  source: JarFile,
  path: String,
  target: File,
) {
  val newPath = "$path/"
  source
    .entries()
    .toList()
    .asSequence()
    .filter { it.name.startsWith(newPath) }
    .filterNot { it.isDirectory }
    .forEach {
      val dest = File(target, it.name.substring(newPath.length))
      dest.parentFile?.mkdirs()
      writeToFile(source.getInputStream(it), dest)
    }
}

/**
 * The JAR file containing the given class.
 * @param clazz The class
 * @return The JAR file or null
 * @throws IOException If there is an I/O error
 */
fun jar(clazz: Class<*>): JarFile? {
  val path = "/${clazz.name.replace('.', '/')}.class"
  val url = clazz.getResource(path) ?: return null
  val jar = url.toString()
  val bang = jar.indexOf('!')
  if (jar.startsWith(JAR_URI_PREFIX) && bang != -1) {
    return JarFile(jar.substring(JAR_URI_PREFIX.length, bang))
  }
  return null
}
