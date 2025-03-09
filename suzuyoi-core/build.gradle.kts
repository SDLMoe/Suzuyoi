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
  implementation(libs.slf4j2)
  implementation(libs.logback)

  implementation(libs.ktor.server.netty)
  implementation(libs.ktor.server.ws)
  implementation(libs.ktor.tls)

  implementation(libs.kaml)
  implementation(libs.kotlinx.coroutines)
  implementation(libs.kotlinx.serialization.json)

  implementation(platform(libs.sdl.bom))
  implementation(libs.sdl.coroutines)
  implementation(libs.sdl.logger.logback)
  implementation(libs.sdl.event)

  implementation(projects.suzuyoiUtils.all)
  implementation(projects.suzuyoiProto)
  implementation(projects.suzuyoiDataProvider)
}

group = "moe.sdl.suzuyoi"
description = "suzuyoi core"
