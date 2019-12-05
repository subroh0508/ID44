import com.android.build.VariantOutput
import com.android.build.gradle.api.ApkVariantOutput
import org.jetbrains.kotlin.gradle.dsl.KotlinJvmOptions
import java.net.InetAddress

plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("android.extensions")
}

// ReactNativeのディレクトリを任意の場所に配置するための設定
// https://github.com/facebook/react-native/blob/e99b926d27fffe8d069f9af75b976845cc3ad43f/react.gradle#L24-L33
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

val vectoricons by extra {
    mapOf(
        "iconFontsDir" to "$REACT_NATIVE_NODE_MODULE_PATH/react-native-vector-icons/Fonts"
    )
}

apply(from = "$REACT_NATIVE_NODE_MODULE_PATH/react-native/react.gradle")
apply(from = "$REACT_NATIVE_NODE_MODULE_PATH/react-native-vector-icons/fonts.gradle")

val enableSeparateBuildPerCPUArchitecture = false

val enableProguardInReleaseBuilds = false

val useIntlJsc = false

androidCommons()
android {
    defaultConfig {
        applicationId = Package.applicationId
        versionCode = Package.versionCode
        versionName = Package.versionName
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
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
}

dependencies {
    implementation(fileTree("dir" to "libs", "include" to listOf("*.jar")))

    implementation(project(":android:components:core"))
    implementation(project(":android:components:authentication"))
    implementation(project(":bridges:timeline"))
    implementation(project(":android:features:authentication"))
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
        val hermesPath = "$REACT_NATIVE_NODE_MODULE_PATH/hermes-engine/android/"
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
