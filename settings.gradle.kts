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
include(":suzuyoi-data-provider")
include(":suzuyoi-proto")
include(":suzuyoi-proto-processor")
include(":suzuyoi-utils").also {
  include(":suzuyoi-utils:all")
  include(":suzuyoi-utils:core")
  include(":suzuyoi-utils:serialization")
  include(":suzuyoi-utils:time")
}

enableFeaturePreview("STABLE_CONFIGURATION_CACHE")
enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
