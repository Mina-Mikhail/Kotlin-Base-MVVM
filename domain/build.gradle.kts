plugins {
  id(Config.Plugins.androidLibrary)
  id(Config.Plugins.kotlinAndroid)
  id(Config.Plugins.kotlinKapt)
  id(Config.Plugins.kotlinParcelize)
}

android {
  compileSdk = Config.AppConfig.compileSdkVersion

  buildTypes {
    getByName("release") {
      proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
    }
  }
}