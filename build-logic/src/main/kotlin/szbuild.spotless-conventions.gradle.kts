plugins {
  id("com.diffplug.spotless")
}

val license: License by rootProject.extra

spotless {
  format("documentation") {
    target("*.adoc", "*.md", "src/**/*.adoc", "src/**/*.md")
    leadingTabsToSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }

  kotlinGradle {
    targetExclude("build-logic/**/build/**")
    ktlint(requiredVersionFromLibs("ktlint"))
    leadingTabsToSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }

  kotlin {
    targetExclude("build/generated/**", "**/src/test/resources/**")
    ktlint(requiredVersionFromLibs("ktlint"))
    licenseHeaderFile(license.headerFile)
    leadingTabsToSpaces(2)
    trimTrailingWhitespace()
    endWithNewline()
  }
}

tasks {
  named("spotlessDocumentation") {
    outputs.doNotCacheIf("negative avoidance savings") { true }
  }
}
