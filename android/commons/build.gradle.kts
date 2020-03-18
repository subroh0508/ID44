import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.library")
    kotlin("android")
}

androidCommons()

android {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.freeCompilerArgs = listOf("-Xallow-result-return-type")
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":shared:util"))
    implementation(project(":data:api"))
    implementation(project(":data:infra:infra-auth"))

    implementation(Libraries.Kotlin.jvm)
    implementation(Libraries.Kotlin.reflect)

    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.Lifecycle.viewModelKtx)
    implementation(Libraries.Jetpack.Lifecycle.liveDataKtx)

    implementation(Libraries.reactNative)
    implementation(project(":react-native-vector-icons"))
    implementation(project(":react-native-fast-image"))

    implementation(Libraries.Kodein.jvm)
    implementation(Libraries.Kodein.androidX)
}


