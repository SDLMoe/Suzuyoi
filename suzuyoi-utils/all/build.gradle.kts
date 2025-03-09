plugins {
  id("szbuild.kotlin-conventions")
}

dependencies {
  project.parent!!
    .subprojects
    .filter {
      it.name != "all"
    }.forEach {
      api(it)
    }
}
