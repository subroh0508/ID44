plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

androidCommons()

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:base"))
    implementation(project(":android:auth"))
    implementation(project(":android:components:core"))
    implementation(project(":libraries:api"))
    implementation(project(":libraries:auth:infra"))
    implementation(project(":libraries:auth:domain:usecase:requestappcredential"))
    implementation(project(":libraries:auth:domain:usecase:requestaccesstoken"))

    implementation(Libraries.Kotlin.stdlibJvm)
    implementation(Libraries.Kotlin.reflect)

    implementation(Libraries.Ktor.clientCommon)
    implementation(Libraries.Ktor.clientAndroid)
    implementation(Libraries.Ktor.loggingJvm)
    implementation(Libraries.Ktor.jsonJvm)
    implementation(Libraries.Ktor.serializationJvm)

    implementation(Libraries.Okhttp3.client)
    implementation(Libraries.Okhttp3.loggingIntercerptor)

    implementation(Libraries.Dagger.core)
    kapt(Libraries.Dagger.compiler)
}
