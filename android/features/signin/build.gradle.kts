import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:base"))
    implementation(project(":android:commons"))
    implementation(project(":shared:util"))
    implementation(project(":shared:model:model-auth"))
    implementation(project(":shared:presentation:presentation-signin"))
    implementation(project(":domain:signin:usecase-signin"))

    implementation(Libraries.Kotlin.jvm)
    implementation(Libraries.Kotlin.reflect)
    implementation(Libraries.Serialization.common)

    implementation(Libraries.Coroutines.common)
    implementation(Libraries.Coroutines.android)

    implementation(Libraries.Jetpack.ktx)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.constraintLayout)
    implementation(Libraries.Jetpack.recyclerView)
    implementation(Libraries.Jetpack.material)
    implementation(Libraries.Jetpack.Lifecycle.extensions)
    implementation(Libraries.Jetpack.Lifecycle.viewModelKtx)

    implementation(Libraries.reactNative)
    implementation(project(":react-native-vector-icons"))
    implementation(project(":react-native-fast-image"))
    implementation(project(":react-native-gesture-handler"))
    implementation(project(":react-native-reanimated"))

    implementation(Libraries.Kodein.jvm)
    implementation(Libraries.Kodein.androidX)
}
