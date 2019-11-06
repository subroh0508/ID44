import com.android.build.VariantOutput
import com.android.build.gradle.api.ApkVariantOutput
import groovy.lang.Closure
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.net.InetAddress

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

val react by extra {
    mapOf(
        "cliPath" to "$REACT_NATIVE_NODE_MODULE_PATH/react-native/cli.js",
        "composeSourceMapsPath" to "$REACT_NATIVE_NODE_MODULE_PATH/react-native/scripts/compose-source-maps.js",
        "entryFile" to "index.js",
        "root" to REACT_NATIVE_PATH,
        "hermesCommand" to "$REACT_NATIVE_NODE_MODULE_PATH/hermes-engine/%OS-BIN%/hermes",
        "enableHermes" to true
    )
}

apply(from = "$REACT_NATIVE_NODE_MODULE_PATH/react-native/react.gradle")
apply(from = "$REACT_NATIVE_NODE_MODULE_PATH/react-native-vector-icons/fonts.gradle")

val enableSeparateBuildPerCPUArchitecture = false

val enableProguardInReleaseBuilds = false

val useIntlJsc = false

android {
    compileSdkVersion(Package.Versions.compileSdk)
    defaultConfig {
        applicationId = Package.applicationId
        minSdkVersion(Package.Versions.minSdk)
        targetSdkVersion(Package.Versions.targetSdk)
        versionCode = Package.versionCode
        versionName = Package.versionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"

        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }

    splits {
        abi {
            reset()
            isEnable = enableSeparateBuildPerCPUArchitecture
            isUniversalApk = false
            include("armeabi-v7a", "x86", "arm64-v8a", "x86_64")
        }
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = enableProguardInReleaseBuilds
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")

            buildConfigField("String", "DEV_HOST", "\"0.0.0.0:8081\"")
        }
        getByName("debug") {
            val host = InetAddress.getLocalHost().hostAddress
            buildConfigField("String", "DEV_HOST", "\"$host:8081\"")
        }
    }

    applicationVariants.forEach { variant ->
        variant.outputs.filterIsInstance<ApkVariantOutput>().forEach { output ->
            val versionCodes = mapOf("armeabi-v7a" to 1, "x86" to 2, "arm64-v8a" to 3, "x86_64" to 4)
            val abi = output.getFilter(VariantOutput.FilterType.ABI)
            if (abi != null) {
                output.versionCodeOverride = (versionCodes[abi] ?: 0) * 1048576 + (defaultConfig.versionCode ?: 1)
            }
        }
    }

    sourceSets.forEach {
        it.java.setSrcDirs(files("src/${it.name}/kotlin"))
    }

    packagingOptions {
        exclude("META-INF/*")
        pickFirst("**/libjsc.so")
        pickFirst("**/libc++_shared.so")
    }

    (kotlinOptions as KotlinJvmOptions).apply {
        freeCompilerArgs = listOf(
                "-Xuse-experimental=kotlin.Experimental"
        )
    }

    lintOptions {
        isCheckReleaseBuilds = false
    }
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:components:core"))
    implementation(project(":android:components:auth"))
    implementation(project(":android:components:timeline"))
    implementation(project(":android:features:auth"))
    implementation(project(":android:features:timeline"))
    implementation(project(":react-native-vector-icons"))

    implementation(Libraries.Kotlin.stdlibJvm)

    implementation(Libraries.Jetpack.ktx)
    implementation(Libraries.Jetpack.appCompat)
    implementation(Libraries.Jetpack.constraintLayout)

    implementation(Libraries.Ktor.serializationJvm)

    implementation(Libraries.reactNative)

    testImplementation(Libraries.JUnit.core)
    androidTestImplementation(Libraries.Jetpack.Test.runner)
    androidTestImplementation(Libraries.Jetpack.Test.espresso)

    if ((react["enableHermes"] as Boolean?) == true) {
        val hermesPath = "$rootDir/frontend/node_modules/hermes-engine/android/"
        implementation(Libraries.Webkit.jscIntl)
        debugImplementation(files(hermesPath + "hermes-debug.aar"))
        releaseImplementation(files(hermesPath + "hermes-release.aar"))
    } else {
        implementation(Libraries.Webkit.jsc)
    }
}

task("copyDownloadableDepsToLibs", Copy::class) {
    from(configurations.compile)
    into("libs")
}

apply(from = "$rootDir/frontend/node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
val applyNativeModulesAppBuildGradle: Closure<Unit> by extra

applyNativeModulesAppBuildGradle(project)
