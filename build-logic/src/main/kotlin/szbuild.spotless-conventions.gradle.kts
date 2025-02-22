plugins {
  id("com.diffplug.spotless")
}

val license: License by rootProject.extra

spotless {
  format("documentation") {
    target("*.adoc", "*.md", "src/**/*.adoc", "src/**/*.md")
    indentWithSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }

  kotlinGradle {
    targetExclude("build-logic/**/build/**")
    ktlint(requiredVersionFromLibs("ktlint"))
    indentWithSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }

  kotlin {
    targetExclude("build/generated/**", "**/src/test/resources/**")
    ktlint(requiredVersionFromLibs("ktlint"))
    licenseHeaderFile(license.headerFile)
    indentWithSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }
}

tasks {
  named("spotlessDocumentation") {
    outputs.doNotCacheIf("negative avoidance savings") { true }
  }
}
