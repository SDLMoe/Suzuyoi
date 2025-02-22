plugins {
  `kotlin-dsl`
  alias(libs.plugins.versions)
}

dependencies {
  implementation(libs.plugins.kotlin.asDep())
  implementation(libs.plugins.foojayResolver.asDep())
  implementation(libs.plugins.shadow.asDep())
  implementation(libs.plugins.githook.asDep())
  implementation(libs.plugins.versions.asDep())
  implementation(libs.plugins.spotless.asDep())
}

// see https://docs.gradle.org/current/userguide/plugins.html#sec:plugin_markers
fun Provider<PluginDependency>.asDep(): Provider<String> =
  map { "${it.pluginId}:${it.pluginId}.gradle.plugin:${it.version}" }
