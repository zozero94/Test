plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
}

android {
    namespace = "zero.app"
    compileSdk = Versions.compileSdk

    defaultConfig {
        applicationId = "zero.app"
        targetSdk = Versions.targetSdk
        versionCode = Versions.versionCode
        versionName = Versions.versionName
        minSdk = Versions.minSdk

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
        }
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }
    composeOptions {
        kotlinCompilerExtensionVersion = Dep.Compose.version
    }
    buildFeatures {
        compose = true
        viewBinding = true
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(Dep.Android.androidxCore)
    implementation(Dep.Android.appCompat)
    implementation(Dep.Lifecycle.viewModel)
    implementation(Dep.Lifecycle.composeViewModel)
    implementation(Dep.Compose.activity)
    implementation(Dep.Compose.ui)
    implementation(Dep.Compose.tooling)
    implementation(Dep.Compose.material)
    implementation(Dep.Kotlin.Coroutine.coroutineAndroid)
    implementation(Dep.Kotlin.Coroutine.coroutineCore)
    implementation(Dep.Kotlin.reflect)
    implementation(Dep.Square.retrofit)
    implementation(Dep.Square.okhttp3Logging)
    implementation(Dep.Square.timber)
    implementation(Dep.Square.serializationConverter)

    implementation(Dep.Accompanist.permissions)
    implementation(Dep.Coil.coil)

    implementation(Dep.Kotlin.Serialization.serialization)
    kapt(Dep.Kotlin.Serialization.serializationPlugin)

    implementation(Dep.Dagger.android)
    kapt(Dep.Dagger.compiler)
    implementation(Dep.inject)
}