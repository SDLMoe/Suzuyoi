plugins {
  id("szbuild.kotlin-conventions")
  alias(libs.plugins.ksp)
}

dependencies {
  implementation(libs.ksp.api)
  implementation(libs.kotlin.poet)
  implementation(libs.kotlin.poet.ksp)

  implementation(libs.wire.runtime)
}

group = "moe.sdl.suzuyoi"
description = "suzuyoi proto kcp processor"
