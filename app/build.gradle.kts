plugins {
  id(Config.Plugins.androidApplication)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinKapt)
  id(Config.Plugins.navigationSafeArgs)
  id(Config.Plugins.hilt)
  id(Config.Plugins.googleServices)
  id(Config.Plugins.crashlytics)
}

android {
  compileSdk = Config.AppConfig.compileSdkVersion

  defaultConfig {
    applicationId = Config.AppConfig.appId
    minSdk = Config.AppConfig.minSdkVersion
    targetSdk = Config.AppConfig.compileSdkVersion
    versionCode = Config.AppConfig.versionCode
    versionName = Config.AppConfig.versionName

    vectorDrawables.useSupportLibrary = true
    multiDexEnabled = true
    testInstrumentationRunner = Config.AppConfig.testRunner
  }

  buildTypes {
    getByName("debug") {
      buildConfigField("String", "API_BASE_URL", Config.Environments.debugBaseUrl)
    }

    getByName("release") {
      isMinifyEnabled = true
      isShrinkResources = true
      buildConfigField("String", "API_BASE_URL", Config.Environments.releaseBaseUrl)
    }
  }

  dataBinding {
    isEnabled = true
  }
}

dependencies {
  implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

  // Networking
  implementation(Libraries.retrofit)
  implementation(Libraries.retrofitConverter)
  implementation(Libraries.gson)
  implementation(Libraries.interceptor)
  implementation(Libraries.chuckLogging)

  // Firebase
  implementation(Libraries.firebaseCore)
  implementation(Libraries.firebaseMessaging)
  implementation(Libraries.firebaseIID)
  implementation(Libraries.crashlytics)

  // Utils
  implementation(Libraries.playServices)
  implementation(Libraries.localization)
  implementation(Libraries.multidex)

  // Hilt
  implementation(Libraries.hilt)
  kapt(Libraries.hiltDaggerCompiler)

  // Project Modules
  implementation(project(Config.Modules.domain))
  implementation(project(Config.Modules.data))
  implementation(project(Config.Modules.presentation))
}

apply {
  from("$rootDir/gradle/install-git-hooks.gradle")
}