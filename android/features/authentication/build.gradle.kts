plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("android.extensions")
    kotlin("kapt")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:base"))
    implementation(project(":android:components:core"))
    implementation(project(":android:components:authentication"))
    implementation(project(":libraries:api"))
    implementation(project(":libraries:auth:infra"))
    implementation(project(":libraries:auth:domain:usecase:requestappcredential"))
    implementation(project(":libraries:auth:domain:usecase:requestaccesstoken"))

    implementation(Libraries.Kotlin.stdlibJvm)
    implementation(Libraries.Kotlin.reflect)
    implementation(Libraries.Kotlin.serializationCommon)

    implementation(Libraries.Coroutines.common)
    implementation(Libraries.Coroutines.android)

    implementation(Libraries.Jetpack.ktx)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.constraintLayout)
    implementation(Libraries.Jetpack.recyclerView)
    implementation(Libraries.Jetpack.material)
    implementation(Libraries.Jetpack.Lifecycle.runtime)
    implementation(Libraries.Jetpack.Lifecycle.extensions)

    implementation(Libraries.reactNative)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)
}
