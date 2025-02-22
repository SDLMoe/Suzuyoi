rootProject.name = "Suzuyoi"

pluginManagement {
  includeBuild("build-logic")
  repositories {
    gradlePluginPortal()
  }
}

plugins {
  id("szbuild.settings-conventions")
}

gitHooks {
  commitMsg {
    conventionalCommits {
      defaultTypes()
    }
  }
  preCommit {
    from(file("gradle/config/githook/precommit.sh"))
  }
  createHooks(true)
}

dependencyResolutionManagement {
  versionCatalogs {
    create("libs") {
      from(files("libs.versions.toml"))
    }
  }
  repositories {
    mavenLocal()
    mavenCentral()
  }
}

include(":suzuyoi-core")
include(":suzuyoi-proto")
include(":suzuyoi-proto-processor")

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
