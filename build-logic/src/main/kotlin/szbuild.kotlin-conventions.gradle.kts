import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  `java-library`
  `maven-publish`
  id("szbuild.java-toolchain-conventions")
  id("szbuild.base-conventions")
  id("com.gradleup.shadow")
  kotlin("jvm")
}

dependencies {
  api(platform(depFromLibs("kotlin-bom")))
  api(depFromLibs("kotlin-stdlib"))
  testImplementation(depFromLibs("kotlin-test"))
  testImplementation(depFromLibs("kotlin-test-junit"))
}

publishing {
  publications.create<MavenPublication>("maven") {
    from(components["java"])
  }
}

tasks.withType<JavaCompile> {
  options.encoding = "UTF-8"
}

tasks.withType<Javadoc> {
  options.encoding = "UTF-8"
}

tasks.shadowJar {
  exclude("**/module-info.class")
  exclude("META-INF/*.SF")
  exclude("META-INF/*.DSA")
  exclude("META-INF/*.RSA")
}
