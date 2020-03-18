import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(Libraries.Kotlin.jvm)
    implementation(Libraries.Kotlin.reflect)

    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.Lifecycle.viewModelKtx)

    implementation(Libraries.reactNative)
    implementation(project(":react-native-vector-icons"))
    implementation(project(":react-native-fast-image"))

    implementation(Libraries.Kodein.jvm)
    implementation(Libraries.Kodein.androidX)
}


