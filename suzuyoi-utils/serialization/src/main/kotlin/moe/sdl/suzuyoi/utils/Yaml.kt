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

import com.charleskorn.kaml.Yaml
import com.charleskorn.kaml.YamlConfiguration

val lenientYaml = Yaml(configuration = YamlConfiguration(strictMode = false))
