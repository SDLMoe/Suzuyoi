plugins {
  application
  id("szbuild.kotlin-conventions")
  alias(libs.plugins.ktor)
  alias(libs.plugins.kotlinx.serialization)
}

application {
  mainClass = "moe.sdl.suzuyoi.SuzoyoiKt"
}

dependencies {
  implementation(libs.ktor.server.netty)
  implementation(libs.ktor.server.ws)
  implementation(libs.ktor.tls)
  implementation(libs.kaml)
  implementation(libs.kotlinx.coroutines)
  implementation(libs.kotlinx.serialization.json)
  implementation(projects.suzuyoiProto)
}

group = "moe.sdl.suzuyoi"
description = "suzuyoi core"
