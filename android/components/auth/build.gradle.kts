plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdkVersion(Package.Versions.compileSdk)

    defaultConfig {
        minSdkVersion(Package.Versions.minSdk)
        targetSdkVersion(Package.Versions.targetSdk)
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    sourceSets.forEach {
        it.java.setSrcDirs(files("src/${it.name}/kotlin"))
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:base"))
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
