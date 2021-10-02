object Config {
  object AppConfig {
    const val appId = "com.mina_mikhail.base_mvvm"
    const val compileSdkVersion = 30
    const val minSdkVersion = 21
    const val versionCode = 1
    const val versionName = "1"
    const val testRunner = "androidx.test.runner.AndroidJUnitRunner"
  }

  object Dependencies {
    const val jitPackURL = "https://jitpack.io"
    const val gradleVersion = "com.android.tools.build:gradle:${Versions.gradleVersion}"
    const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
    const val navigationSafeArgs =
      "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidNavigation}"
    const val hilt = "com.google.dagger:hilt-android-gradle-plugin:${Versions.hiltVersion}"
    const val googleServices = "com.google.gms:google-services:${Versions.googleServicesClassPath}"
    const val crashlytics = "com.google.firebase:firebase-crashlytics-gradle:${Versions.crashlyticsClassPath}"
  }

  object Plugins {
    const val androidApplication = "com.android.application"
    const val kotlinAndroid = "kotlin-android"
    const val kotlinKapt = "kotlin-kapt"
    const val navigationSafeArgs = "androidx.navigation.safeargs"
    const val hilt = "dagger.hilt.android.plugin"
    const val googleServices = "com.google.gms.google-services"
    const val crashlytics = "com.google.firebase.crashlytics"
    const val androidLibrary = "com.android.library"
    const val kotlinJvm = "org.jetbrains.kotlin.jvm"
    const val ktLint = "org.jlleitschuh.gradle.ktlint"
  }

  object Modules {
    const val domain = ":domain"
    const val data = ":data"
    const val presentation = ":presentation"
    const val prettyPopUp = ":prettyPopUp"
    const val actionChooser = ":actionChooser"
    const val appTutorial = ":appTutorial"
    const val imagesSlider = ":imagesSlider"
  }

  object Environments {
    const val debugBaseUrl = "\"http://url.to.server/api/\""
    const val releaseBaseUrl = "\"http://url.to.server/api/\""
  }
}