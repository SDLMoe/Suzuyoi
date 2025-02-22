plugins {
  id("com.github.ben-manes.versions")
}

tasks.dependencyUpdates {
  checkConstraints = true
  resolutionStrategy {
    componentSelection {
      val regexes = listOf("alpha", "beta", "rc", "cr", "m", "preview", "b", "ea")
        .map { qualifier -> Regex("""(?i).*[.-]$qualifier[.\d-+]*""") }
      all {
        val rejected = regexes.any { it.matches(candidate.version) }
        if (rejected) {
          reject("Release candidate")
        }
      }
    }
  }
}
