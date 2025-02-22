package moe.sdl.suzuyoi.proto.processor

import com.google.devtools.ksp.processing.SymbolProcessor
import com.google.devtools.ksp.processing.SymbolProcessorEnvironment
import com.google.devtools.ksp.processing.SymbolProcessorProvider

class Provider : SymbolProcessorProvider {
  override fun create(environment: SymbolProcessorEnvironment): SymbolProcessor {
    return Processor(
      options = environment.options,
      logger = environment.logger,
      codeGenerator = environment.codeGenerator,
    )
  }
}
