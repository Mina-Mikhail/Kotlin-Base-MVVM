plugins {
  id(Config.Plugins.androidLibrary)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinKapt)
  id(Config.Plugins.hilt)
  id(Config.Plugins.navigationSafeArgs)
  id(Config.Plugins.googleServices)
}

android {
  compileSdk = Config.AppConfig.compileSdkVersion

  defaultConfig {
    minSdk = Config.AppConfig.minSdkVersion

    vectorDrawables.useSupportLibrary = true
  }

  buildTypes {
    getByName("release") {
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }

  dataBinding {
    isEnabled = true
  }
}

dependencies {

  // Support
  implementation(Libraries.appCompat)
  implementation(Libraries.coreKtx)
  implementation(Libraries.androidSupport)

  // Arch Components
  implementation(Libraries.viewModel)
  implementation(Libraries.lifeData)
  implementation(Libraries.lifecycle)
  implementation(Libraries.viewModelState)

  // Kotlin Coroutines
  implementation(Libraries.coroutinesCore)
  implementation(Libraries.coroutinesAndroid)

  // UI
  implementation(Libraries.materialDesign)
  implementation(Libraries.navigationFragment)
  implementation(Libraries.navigationUI)
  implementation(Libraries.loadingAnimations)
  implementation(Libraries.alerter)
  implementation(Libraries.coil)
  implementation(Libraries.gifDrawable)

  // Firebase
  implementation(Libraries.firebaseCore)
  implementation(Libraries.firebaseMessaging)
  implementation(Libraries.firebaseIID)

  // Utils
  implementation(Libraries.playServices)
  implementation(Libraries.localization)
  implementation(Libraries.permissions)
  implementation(Libraries.gson)

  // Hilt
  implementation(Libraries.hilt)
  kapt(Libraries.hiltDaggerCompiler)

  // Map
  implementation(Libraries.map)
  implementation(Libraries.playServicesLocation)
  implementation(Libraries.rxLocation)

  // Project Modules
  implementation(project(Config.Modules.domain))
  implementation(project(Config.Modules.prettyPopUp))
  implementation(project(Config.Modules.actionChooser))
  implementation(project(Config.Modules.appTutorial))
  implementation(project(Config.Modules.imagesSlider))
}
