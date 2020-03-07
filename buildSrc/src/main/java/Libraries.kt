@Suppress("unused")
object Libraries {
    object GradlePlugin {
        const val androidGradlePluginVersion = "3.6.1"

        const val android = "com.android.tools.build:gradle:$androidGradlePluginVersion"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Kotlin.version}"
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Kotlin.version}"
    }

    object Kotlin {
        const val version = "1.3.70"

        const val common = "org.jetbrains.kotlin:kotlin-stdlib-common:$version"
        const val jvm = "org.jetbrains.kotlin:kotlin-stdlib:$version"
        const val js = "org.jetbrains.kotlin:kotlin-stdlib-js:$version"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:$version"
        const val test = "org.jetbrains.kotlin:kotlin-test:$version"
    }

    object Serialization {
        const val version = "0.14.0"

        const val common = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:$version"
        const val jvm = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:$version"
        const val js = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:$version"
        const val native = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:$version"

        /* Since v0.20.0
        object Properties {
            const val common = "org.jetbrains.kotlinx:kotlinx-serialization-properties-common:$version"
            const val jvm = "org.jetbrains.kotlinx:kotlinx-serialization-properties:$version"
            const val js = "org.jetbrains.kotlinx:kotlinx-serialization-properties-js:$version"
            const val native = "org.jetbrains.kotlinx:kotlinx-serialization-properties-native:$version"
        }
        */
    }

    const val reactNative = "com.facebook.react:react-native:+"

    object Webkit {
        const val jscIntl = "org.webkit:android-jsc-intl:+"
        const val jsc = "org.webkit:android-jsc:+"
    }

    object Coroutines {
        const val version = "1.3.4"

        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val js = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:$version"
    }

    object Jetpack {
        const val coreVersion = "1.2.0"
        const val appCompatVersion = "1.1.0"
        const val fragmentVersion = "1.1.0"
        const val mediaVersion = "1.0.0"
        const val preferenceVersion = "1.0.0"
        const val cardViewVersion = "1.0.0"
        const val recyclerViewVersion = "1.1.0"
        const val materialVersion = "1.0.0"
        const val vectorDrawableVersion = "1.0.0"
        const val constraintLayoutVersion = "2.0.0-beta4"
        const val lifecycleVersion = "2.2.0"

        const val testRunnerVersion = "1.1.0"
        const val espressoVersion = "3.0.2"

        const val core = "androidx.core:core:$coreVersion"
        const val ktx = "androidx.core:core-ktx:$coreVersion"
        const val appCompat = "androidx.appcompat:appcompat:$appCompatVersion"
        const val fragment = "androidx.fragment:fragment:$fragmentVersion"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:$fragmentVersion"
        const val media = "androidx.media:media:$mediaVersion"
        const val preference = "androidx.preference:preference:$preferenceVersion"
        const val cardView = "androidx.cardview:cardview:$cardViewVersion"
        const val recyclerView = "androidx.recyclerview:recyclerview:$recyclerViewVersion"
        const val material = "com.google.android.material:material:$materialVersion"
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable-animated:$vectorDrawableVersion"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:$constraintLayoutVersion"

        const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
        const val lifecycleLiveData = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"

        object Lifecycle {
            const val extensions = "androidx.lifecycle:lifecycle-extensions:$lifecycleVersion"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$lifecycleVersion"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:$lifecycleVersion"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:$lifecycleVersion"
        }

        object Test {
            const val runner = "androidx.test:runner:$testRunnerVersion"
            const val espresso = "com.android.support.test.espresso:espresso-core:$espressoVersion"
        }
    }

    object Ktor {
        const val version = "1.3.1"

        const val clientCommon = "io.ktor:ktor-client-core:$version"
        const val clientAndroid = "io.ktor:ktor-client-okhttp:$version"
        const val clientIos = "io.ktor:ktor-client-ios:$version"
        const val clientJs = "io.ktor:ktor-client-js:$version"
        const val loggingJvm = "io.ktor:ktor-client-logging-jvm:$version"
        const val jsonCommon = "io.ktor:ktor-client-json:$version"
        const val jsonJvm = "io.ktor:ktor-client-json-jvm:$version"
        const val jsonNative = "io.ktor:ktor-client-json-native:$version"
        const val jsonJs = "io.ktor:ktor-client-json-js:$version"
        const val serializationCommon = "io.ktor:ktor-client-serialization:$version"
        const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:$version"
        const val serializationIosArm64 = "io.ktor:ktor-client-serialization-iosarm64:$version"
        const val serializationIosX64 = "io.ktor:ktor-client-serialization-iosx64:$version"
        const val serializationJs = "io.ktor:ktor-client-serialization-js:$version"
    }

    object Okhttp3 {
        const val version = "3.14.2"

        const val client = "com.squareup.okhttp3:okhttp:$version"
        const val loggingIntercerptor = "com.squareup.okhttp3:logging-interceptor:$version"
    }

    object Klock {
        const val version = "1.9.1"

        const val common = "com.soywiz.korlibs.klock:klock:$version"
        const val android = "com.soywiz.korlibs.klock:klock-android:$version"
        const val js = "com.soywiz.korlibs.klock:klock-js:$version"
    }

    object Kodein {
        const val version = "6.5.3"

        const val common = "org.kodein.di:kodein-di-erased:$version"
        const val jvm = "org.kodein.di:kodein-di-generic-jvm:$version"
        const val androidX = "org.kodein.di:kodein-di-framework-android-x:$version"
        const val js = "org.kodein.di:kodein-di-erased-js:$version"
    }

    object JUnit {
        const val coreVersion = "4.12"
        const val platformRunnerVersion = "1.3.0"
        const val vintageVersion = "5.2.0"

        const val core = "junit:junit:$coreVersion"
    }

    object MockK {
        const val version = "1.9.3"

        const val core = "io.mockk:mockk:$version"
    }
}
