rootProject.name = "Kotlin-Base-MVVM"

// Include all the existent modules in the project
rootDir
  .walk()
  .maxDepth(1)
  .filter {
    it.name != rootProject.name &&
      (
        it.name != "buildSrc" && it.isDirectory && file("${it.absolutePath}/build.gradle.kts").exists() ||
          it.name != "buildSrc" && it.isDirectory && file("${it.absolutePath}/build.gradle").exists()
        )
  }
  .forEach {
    include(":${it.name}")
  }