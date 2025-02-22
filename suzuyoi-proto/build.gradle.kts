plugins {
  id("szbuild.kotlin-conventions")
  alias(libs.plugins.wire)
  alias(libs.plugins.ksp)
}

wire {
  sourcePath {
    srcDir("src/main/protos")
  }
  kotlin {
    rpcRole = "server"
    rpcCallStyle = "suspending"
    singleMethodServices = false
  }
}

dependencies {
  ksp(projects.suzuyoiProtoProcessor)
}

group = "moe.sdl.suzuyoi"
description = "suzuyoi proto"
