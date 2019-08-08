plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
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

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:api"))
                implementation(project(":libraries:timeline:domain:entity"))
                implementation(project(":libraries:timeline:domain:valueobject"))

                implementation(Libraries.Kotlin.stdlibCommon)
                implementation(Libraries.Kotlin.serializationCommon)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":android:base"))

                implementation(Libraries.Kotlin.stdlibJvm)
                implementation(Libraries.Kotlin.serializationJvm)

                implementation(Libraries.Coroutines.android)

                implementation(Libraries.Jetpack.ktx)

                implementation(Libraries.Klock.jvm)

                implementation(Libraries.Dagger.core)
            }
        }
    }
}

withGroovyBuilder {
    "dependencies" {
        "kapt"(Libraries.Dagger.compiler)
    }
}
