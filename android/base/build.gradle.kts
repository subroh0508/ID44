plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":react-native-vector-icons"))

    implementation(Libraries.Kotlin.stdlibJvm)
    implementation(Libraries.Kotlin.reflect)

    implementation(Libraries.Coroutines.common)
    implementation(Libraries.Coroutines.android)

    implementation(Libraries.Jetpack.core)
    implementation(Libraries.Jetpack.ktx)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.fragment)
    implementation(Libraries.Jetpack.fragmentKtx)

    implementation(Libraries.reactNative)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)
}
