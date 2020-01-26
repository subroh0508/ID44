import com.android.build.gradle.BaseExtension
import org.gradle.api.JavaVersion
import org.gradle.api.Project
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.api.plugins.ExtensionAware
import org.gradle.kotlin.dsl.withType
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

internal fun DependencyHandler.implementation(dependency: Any) {
    add("implementation", dependency)
}

internal fun DependencyHandler.testImplementation(dependency: Any) {
    add("testImplementation", dependency)
}

internal fun DependencyHandler.androidTestImplementation(dependency: Any) {
    add("androidTestImplementation", dependency)
}

internal fun DependencyHandler.kapt(dependency: Any) {
    add("kapt", dependency)
}

fun Project.androidExt(configure: BaseExtension.() -> Unit) =
    (this as ExtensionAware).extensions.configure("android", configure)

internal fun Project.androidShared() {
    androidExt {
        compileSdkVersion(Package.Versions.compileSdk)

        defaultConfig {
            minSdkVersion(Package.Versions.minSdk)
            targetSdkVersion(Package.Versions.targetSdk)

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
        }

        buildTypes {
            getByName("release") {
                isMinifyEnabled = false
                proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
            }
        }
    }

    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = "1.8"
    }
}

fun Project.androidCommons() {
    androidShared()
    androidExt {
        sourceSets.forEach {
            it.java.setSrcDirs(files("src/${it.name}/kotlin"))
        }
    }
}

fun Project.androidMPP() {
    androidShared()
    androidExt {
        sourceSets.forEach {
            if (it.name.startsWith("androidTest")) {
                return@forEach
            }
            it.java.setSrcDirs(files("src/android${it.name.toUpperFirstChar()}/kotlin"))
            it.manifest.srcFile("src/androidMain/AndroidManifest.xml")
        }
    }
}

private fun String.toUpperFirstChar() = when (length) {
    0 -> ""
    1 -> toUpperCase()
    else -> get(0).toUpperCase() + substring(1)
}
