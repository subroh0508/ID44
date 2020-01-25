@Suppress("unused")
object Libraries {
    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Versions.buildTools}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }

    object Kotlin {
        const val stdlibCommon = "org.jetbrains.kotlin:kotlin-stdlib-common:${Versions.kotlin}"
        const val stdlibJvm = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.kotlin}"
        const val stdlibJs = "org.jetbrains.kotlin:kotlin-stdlib-js:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val serializationCommon = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-common:${Versions.kotlinxSerialization}"
        const val serializationJvm = "org.jetbrains.kotlinx:kotlinx-serialization-runtime:${Versions.kotlinxSerialization}"
        const val serializationJs = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-js:${Versions.kotlinxSerialization}"
        const val serializationIos = "org.jetbrains.kotlinx:kotlinx-serialization-runtime-native:${Versions.kotlinxSerialization}"
        const val androidExtensions = "org.jetbrains.kotlin:kotlin-android-extensions-runtime:${Versions.kotlin}"
        const val test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    }

    const val reactNative = "com.facebook.react:react-native:+"

    object Webkit {
        const val jscIntl = "org.webkit:android-jsc-intl:+"
        const val jsc = "org.webkit:android-jsc:+"
    }

    object Coroutines {
        const val common = "org.jetbrains.kotlinx:kotlinx-coroutines-core-common:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val js = "org.jetbrains.kotlinx:kotlinx-coroutines-core-js:${Versions.coroutines}"
    }

    object Jetpack {
        const val core = "androidx.core:core:${Versions.Jetpack.core}"
        const val ktx = "androidx.core:core-ktx:${Versions.Jetpack.core}"
        const val appCompat = "androidx.appcompat:appcompat:${Versions.Jetpack.appCompat}"
        const val fragment = "androidx.fragment:fragment:${Versions.Jetpack.fragment}"
        const val fragmentKtx = "androidx.fragment:fragment-ktx:${Versions.Jetpack.fragment}"
        const val media = "androidx.media:media:${Versions.Jetpack.media}"
        const val preference = "androidx.preference:preference:${Versions.Jetpack.preference}"
        const val cardView = "androidx.cardview:cardview:${Versions.Jetpack.cardView}"
        const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.Jetpack.recyclerView}"
        const val material = "com.google.android.material:material:${Versions.Jetpack.material}"
        const val vectorDrawable = "androidx.vectordrawable:vectordrawable-animated:${Versions.Jetpack.vectorDrawable}"
        const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.Jetpack.constraintLayout}"

        object Lifecycle {
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Jetpack.lifecycle}"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.Jetpack.lifecycle}"
            const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.Jetpack.lifecycle}"
            const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.Jetpack.lifecycle}"
        }

        object Test {
            const val runner = "androidx.test:runner:${Versions.Jetpack.testRunner}"
            const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.Jetpack.espresso}"
        }
    }

    object Ktor {
        const val clientCommon = "io.ktor:ktor-client-core:${Versions.ktor}"
        const val clientAndroid = "io.ktor:ktor-client-okhttp:${Versions.ktor}"
        const val clientIos = "io.ktor:ktor-client-ios:${Versions.ktor}"
        const val clientJs = "io.ktor:ktor-client-js:${Versions.ktor}"
        const val loggingJvm = "io.ktor:ktor-client-logging-jvm:${Versions.ktor}"
        const val jsonCommon = "io.ktor:ktor-client-json:${Versions.ktor}"
        const val jsonJvm = "io.ktor:ktor-client-json-jvm:${Versions.ktor}"
        const val jsonNative = "io.ktor:ktor-client-json-native:${Versions.ktor}"
        const val jsonJs = "io.ktor:ktor-client-json-js:${Versions.ktor}"
        const val serializationCommon = "io.ktor:ktor-client-serialization:${Versions.ktor}"
        const val serializationJvm = "io.ktor:ktor-client-serialization-jvm:${Versions.ktor}"
        const val serializationIosArm64 = "io.ktor:ktor-client-serialization-iosarm64:${Versions.ktor}"
        const val serializationIosX64 = "io.ktor:ktor-client-serialization-iosx64:${Versions.ktor}"
        const val serializationJs = "io.ktor:ktor-client-serialization-js:${Versions.ktor}"
    }

    object Okhttp3 {
        const val client = "com.squareup.okhttp3:okhttp:${Versions.okhttp3}"
        const val loggingIntercerptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okhttp3}"
    }

    object Klock {
        const val common = "com.soywiz.korlibs.klock:klock:${Versions.klock}"
        const val android = "com.soywiz.korlibs.klock:klock-android:${Versions.klock}"
        const val js = "com.soywiz.korlibs.klock:klock-js:${Versions.klock}"
    }

    object Dagger {
        const val core = "com.google.dagger:dagger:${Versions.dagger}"
        const val android = "com.google.dagger:dagger-android:${Versions.dagger}"
        const val androidSupport = "com.google.dagger:dagger-android-support:${Versions.dagger}"
        const val compiler = "com.google.dagger:dagger-compiler:${Versions.dagger}"
        const val androidProcessor = "com.google.dagger:dagger-android-processor:${Versions.dagger}"
    }

    object Kodein {
        const val erasedCommon = "org.kodein.di:kodein-di-erased:${Versions.kodein}"
        const val genericJvm = "org.kodein.di:kodein-di-generic-jvm:${Versions.kodein}"
        const val androidX = "org.kodein.di:kodein-di-framework-android-x:${Versions.kodein}"
        const val erasedJs = "org.kodein.di:kodein-di-generic-js:${Versions.kodein}"
    }

    object JUnit {
        const val core = "junit:junit:${Versions.JUnit.core}"
        const val platformRunner = "org.junit.platform:junit-platform-runner:${Versions.JUnit.platformRunner}"
        const val junitVintage = "org.junit.vintage:junit-vintage-engine:${Versions.JUnit.vintage}"
    }

    object Robolectric {
        const val core = "org.robolectric:robolectric:${Versions.robolectric}"
        const val shadows = "org.robolectric:shadows-supportv4:${Versions.robolectric}"
    }

    const val mockk = "io.mockk:mockk:${Versions.mockk}"

    object Spek {
        const val dslJvm = "org.spekframework.spek2:spek-dsl-jvm:${Versions.spek}"
        const val junit = "org.spekframework.spek2:spek-runner-junit5:${Versions.spek}"
    }
}
