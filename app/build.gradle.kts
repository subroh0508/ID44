import Libraries
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

android {
    compileSdkVersion(28)
    defaultConfig {
        applicationId = "id44.mizuki"
        minSdkVersion(21)
        targetSdkVersion(28)
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
    packagingOptions {
        exclude("META-INF/*")
    }
    (kotlinOptions as KotlinJvmOptions).apply {
        freeCompilerArgs = listOf(
                "-Xuse-experimental=kotlin.Experimental"
        )
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))
    implementation(Libraries.Kotlin.stdlib)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.constraintLayout)
    testImplementation("junit:junit:4.12")
    androidTestImplementation(Libraries.Jetpack.Test.runner)
    androidTestImplementation(Libraries.Jetpack.Test.espresso)
}
