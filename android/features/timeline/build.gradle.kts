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
    implementation(project(":android:auth"))
    implementation(project(":bridges:bridges-shared"))
    implementation(project(":bridges:bridges-auth"))
    implementation(project(":bridges:bridges-timeline"))
    implementation(project(":shared:util"))
    implementation(project(":data:infra:infra-auth"))
    implementation(project(":shared:model:model-account"))
    implementation(project(":domain:timeline:usecase-timeline"))
    implementation(project(":shared:model:model-status"))
    implementation(project(":react-native-vector-icons"))
    implementation(project(":react-native-gesture-handler"))
    implementation(project(":react-native-reanimated"))
    implementation(project(":react-native-fast-image"))

    implementation(Libraries.Kotlin.jvm)
    implementation(Libraries.Kotlin.reflect)
    implementation(Libraries.Serialization.common)

    implementation(Libraries.Coroutines.common)
    implementation(Libraries.Coroutines.android)

    implementation(Libraries.Klock.android)

    implementation(Libraries.Jetpack.ktx)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.constraintLayout)
    implementation(Libraries.Jetpack.recyclerView)
    implementation(Libraries.Jetpack.material)
    implementation(Libraries.Jetpack.Lifecycle.extensions)
    implementation(Libraries.Jetpack.Lifecycle.viewModelKtx)
    implementation(Libraries.Jetpack.Lifecycle.liveDataKtx)

    implementation(Libraries.reactNative)

    implementation(Libraries.Kodein.jvm)
    implementation(Libraries.Kodein.androidX)
}
