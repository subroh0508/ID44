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
    implementation(project(":android:auth"))
    implementation(project(":android:components:core"))
    implementation(project(":android:components:timeline"))
    implementation(project(":libraries:shared"))
    implementation(project(":libraries:auth:infra"))
    implementation(project(":libraries:auth:domain:usecase:switchaccesstoken"))
    implementation(project(":libraries:account:domain:usecase:fetchownaccounts"))
    implementation(project(":libraries:account:domain:entity"))
    implementation(project(":libraries:timeline:domain:usecase:subscribe"))
    implementation(project(":libraries:timeline:domain:usecase:unsubscribe"))
    implementation(project(":libraries:timeline:domain:entity"))
    implementation(project(":libraries:timeline:domain:valueobject"))
    implementation(project(":react-native-vector-icons"))
    implementation(project(":react-native-gesture-handler"))
    implementation(project(":react-native-reanimated"))

    implementation(Libraries.Kotlin.stdlibJvm)
    implementation(Libraries.Kotlin.reflect)
    implementation(Libraries.Kotlin.serializationCommon)

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

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)
}
