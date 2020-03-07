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
    implementation(project(":libraries:shared"))
    implementation(project(":libraries:auth:auth-infra"))
    implementation(project(":libraries:auth:domain:usecase:switchaccesstoken"))
    implementation(project(":libraries:account:domain:usecase:fetchownaccount"))
    implementation(project(":libraries:account:domain:usecase:fetchownaccounts"))
    implementation(project(":libraries:account:domain:account-entity"))
    implementation(project(":libraries:timeline:domain:usecase:fetchstatuses"))
    implementation(project(":libraries:timeline:domain:usecase:subscribe"))
    implementation(project(":libraries:timeline:domain:usecase:unsubscribe"))
    implementation(project(":libraries:timeline:domain:usecase:submitstatus"))
    implementation(project(":libraries:timeline:domain:usecase:togglefavourite"))
    implementation(project(":libraries:timeline:domain:usecase:togglereblog"))
    implementation(project(":libraries:timeline:domain:timeline-entity"))
    implementation(project(":libraries:timeline:domain:timeline-valueobject"))
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
