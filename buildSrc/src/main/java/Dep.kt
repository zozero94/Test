object Versions {
    const val compileSdk = 33
    const val buildTools = "30.0.3"

    const val minSdk = 23
    const val targetSdk = 33
    const val versionCode = 1
    const val versionName = "1.0.0"
}

object Dep {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.1.2"
    const val startUp = "androidx.startup:startup-runtime:1.1.0"

    object Android {
        const val androidxCore = "androidx.core:core-ktx:1.9.0"
        const val appCompat = "androidx.appcompat:appcompat:1.5.1"
    }

    object Lifecycle {
        const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1"
        const val composeViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:2.5.1"
    }

    object Coil {
        const val coil = "io.coil-kt:coil-compose:2.2.1"
    }

    object Compose {
        const val version = "1.4.0-alpha02"

        const val ui = "androidx.compose.ui:ui:${version}"
        const val material = "androidx.compose.material:material:${version}"
        const val tooling = "androidx.compose.ui:ui-tooling:${version}"
        const val activity = "androidx.activity:activity-compose:1.5.1"
        const val viewBinding = "androidx.compose.ui:ui-viewbinding:1.2.1"
    }

    object Accompanist{
        const val Pager = "com.google.accompanist:accompanist-pager:0.28.0"
    }

    object Kotlin {
        private const val version = "1.7.21"

        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val parcelizePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.20"
        object Serialization{
            const val serialization = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.4.1"
            const val serializationPlugin = "org.jetbrains.kotlin:kotlin-serialization:$version"
        }

        object Coroutine {
            private const val coroutineVersion = "1.6.1"
            const val coroutineCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$coroutineVersion"
            const val coroutineAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$coroutineVersion"
        }

        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:1.6.0"
    }

    object Dagger {
        private const val daggerVersion = "2.44"
        const val android = "com.google.dagger:hilt-android:$daggerVersion"
        const val compiler = "com.google.dagger:hilt-compiler:$daggerVersion"
        const val androidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:$daggerVersion"

    }

    object Square {
        const val retrofit = "com.squareup.retrofit2:retrofit:2.9.0"
        const val okhttp3Logging = "com.squareup.okhttp3:logging-interceptor:4.9.1"
        const val serializationConverter = "com.jakewharton.retrofit:retrofit2-kotlinx-serialization-converter:0.8.0"
        const val timber = "com.jakewharton.timber:timber:4.7.1"
    }


    object Test {
        const val junit5 = "org.junit.jupiter:junit-jupiter-api:5.8.1"
        const val Junit5Engine = "org.junit.jupiter:junit-jupiter-engine:5.8.1"
        const val coroutineTest = "org.jetbrains.kotlinx:kotlinx-coroutines-test:1.5.1"
        const val robolectric = "org.robolectric:robolectric:4.8.1"
        const val mockk = "io.mockk:mockk:1.12.5"
        const val hiltTest = "com.google.dagger:hilt-android-testing:2.38.1"
    }

    const val inject = "javax.inject:javax.inject:1"
}