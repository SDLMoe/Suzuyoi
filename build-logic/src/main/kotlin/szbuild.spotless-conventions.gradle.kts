plugins {
  id("com.diffplug.spotless")
}

val license: License by rootProject.extra

spotless {

  format("misc") {
    target("*.gradle.kts", "build-logic/**/*.gradle.kts", "*.gitignore")
    targetExclude("build-logic/**/build/**")
    indentWithSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }

  format("documentation") {
    target("*.adoc", "*.md", "src/**/*.adoc", "src/**/*.md")
    indentWithSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }


  pluginManager.withPlugin("org.jetbrains.kotlin.jvm") {
    kotlin {
      targetExclude("**/src/test/resources/**")
      ktlint(requiredVersionFromLibs("ktlint"))
      licenseHeaderFile(license.headerFile)
      indentWithSpaces(2)
      trimTrailingWhitespace()
      endWithNewline()
    }
  }
}

tasks {
  named("spotlessDocumentation") {
    outputs.doNotCacheIf("negative avoidance savings") { true }
  }
  named("spotlessMisc") {
    outputs.doNotCacheIf("negative avoidance savings") { true }
  }
}
