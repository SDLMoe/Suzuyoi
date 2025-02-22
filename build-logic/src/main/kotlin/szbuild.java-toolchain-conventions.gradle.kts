import org.jetbrains.kotlin.gradle.dsl.KotlinJvmProjectExtension

project.pluginManager.withPlugin("java") {
  val javaLanguageVersion = JavaLanguageVersion.of(21)
  the<JavaPluginExtension>().apply {
    toolchain {
      languageVersion = javaLanguageVersion
    }
  }

  pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
    configure<KotlinJvmProjectExtension> {
      jvmToolchain {
        languageVersion = javaLanguageVersion
      }
    }
  }
}
