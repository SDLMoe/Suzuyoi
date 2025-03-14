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

/**
 * Replace placeholder like {0} {1} ... {n} with parameter [args]
 * @param args list to replace
 * @receiver string like: 'string with parameter {0} and {1}'
 * @return replaced string
 */
fun String.replaceWithOrder(vararg args: Any?): String {
  if (args.isEmpty() || isEmpty()) {
    return this
  }
  val chars = toCharArray()
  val builder = StringBuilder(length)
  var i = 0
  while (i < chars.size) {
    val mark = i
    if (chars[i] == '{') {
      var num = 0
      while (i + 1 < chars.size && Character.isDigit(chars[i + 1])) {
        i++
        num *= 10
        num += chars[i] - '0'
      }
      if (i != mark && i + 1 < chars.size && chars[i + 1] == '}') {
        i++
        builder.append(args.getOrNull(num)?.toString() ?: "{$num}")
      } else {
        i = mark
      }
    }
    if (mark == i) {
      builder.append(chars[i])
    }
    i++
  }
  return builder.toString()
}

fun String.beginWithUpperCase(): String =
  when (this.length) {
    0 -> ""
    1 -> this.uppercase()
    else -> this[0].uppercase() + this.substring(1)
  }

fun String.toCamelCase(): String =
  this.lowercase().split('_').joinToString("") {
    it.beginWithUpperCase()
  }
