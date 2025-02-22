plugins {
  id("szbuild.base-conventions")
  id("szbuild.dependency-update")
  kotlin("jvm")
}

val license by extra(License(
  name = "The MIT License",
  url = uri("https://github.com/SDLMoe/Suzuyoi/blob/main/LICENSE"),
  headerFile = layout.projectDirectory.file("gradle/config/spotless/mit-license.kt")
))
