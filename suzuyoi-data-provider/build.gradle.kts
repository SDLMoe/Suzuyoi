plugins {
  id("szbuild.kotlin-conventions")
  alias(libs.plugins.kotlinx.atomicfu)
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  implementation(platform(libs.sdl.bom))
  implementation(libs.sdl.coroutines)
  implementation(libs.slf4j2)
  implementation(libs.kotlinx.coroutines)
  implementation(libs.kotlinx.serialization.core)
  implementation(libs.kotlinx.serialization.json)

  implementation(libs.fswatch)

  implementation(projects.suzuyoiUtils.core)
  implementation(projects.suzuyoiUtils.serialization)
}
