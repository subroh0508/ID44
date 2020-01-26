plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:base"))
    implementation(project(":bridges:auth"))
    implementation(project(":libraries:api"))
    implementation(project(":libraries:shared"))
    implementation(project(":libraries:auth:infra"))
    implementation(project(":libraries:auth:domain:auth-valueobject"))

    implementation(Libraries.Kotlin.stdlibJvm)
    implementation(Libraries.Kotlin.reflect)

    implementation(Libraries.Coroutines.common)
    implementation(Libraries.Coroutines.android)

    implementation(Libraries.Jetpack.core)
    implementation(Libraries.Jetpack.ktx)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.fragment)
    implementation(Libraries.Jetpack.fragmentKtx)
    implementation(Libraries.Jetpack.Lifecycle.extensions)
    implementation(Libraries.Jetpack.Lifecycle.viewModelKtx)
    implementation(Libraries.Jetpack.Lifecycle.liveDataKtx)

    implementation(Libraries.reactNative)

    implementation(Libraries.Kodein.genericJvm)
    implementation(Libraries.Kodein.androidX)
}
