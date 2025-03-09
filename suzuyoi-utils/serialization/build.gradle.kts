plugins {
  id("szbuild.kotlin-conventions")
  alias(libs.plugins.kotlinx.serialization)
}

dependencies {
  api(libs.kotlinx.serialization.json)
  api(libs.kaml)
  testImplementation(libs.kotlin.test)
}
