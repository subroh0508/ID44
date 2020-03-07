plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:base"))
    implementation(project(":bridges:bridges-auth"))
    implementation(project(":data:api"))
    implementation(project(":shared:util"))
    implementation(project(":data:infra:infra-auth"))
    implementation(project(":shared:model:model-auth"))

    implementation(Libraries.Kotlin.jvm)
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

    implementation(Libraries.Kodein.jvm)
    implementation(Libraries.Kodein.androidX)
}
