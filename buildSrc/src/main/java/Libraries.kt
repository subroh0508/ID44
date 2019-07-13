@Suppress("unused")
object Libraries {
    object GradlePlugin {
        const val android = "com.android.tools.build:gradle:${Versions.buildTools}"
        const val kotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        const val kotlinSerialization = "org.jetbrains.kotlin:kotlin-serialization:${Versions.kotlin}"
    }

    object Kotlin {
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
        const val reflect = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
        const val androidExtensions = "org.jetbrains.kotlin:kotlin-android-extensions-runtime:${Versions.kotlin}"
        const val test = "org.jetbrains.kotlin:kotlin-test:${Versions.kotlin}"
    }

    const val reactNative = "com.facebook.react:react-native:+"

    object Coroutines {
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
        const val rxJava2 = "org.jetbrains.kotlinx:kotlinx-coroutines-rx2:${Versions.coroutines}"
    }

    object Jetpack {
        const val core = "androidx.core:core:${Versions.Jetpack.core}"
        const val ktx = "androidx.core:core-ktx:${Versions.Jetpack.ktx}"
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
            const val runtime = "androidx.lifecycle:lifecycle-runtime:${Versions.Jetpack.lifecycle}"
            const val extensions = "androidx.lifecycle:lifecycle-extensions:${Versions.Jetpack.lifecycle}"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:${Versions.Jetpack.lifecycle}"
        }

        object Test {
            const val runner = "androidx.test:runner:${Versions.Jetpack.testRunner}"
            const val espresso = "com.android.support.test.espresso:espresso-core:${Versions.Jetpack.espresso}"
        }
    }
}
