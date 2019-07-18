import groovy.lang.Closure

apply(from = "$rootDir/frontend/node_modules/@react-native-community/cli-platform-android/native_modules.gradle")
val applyNativeModulesSettingsGradle: Closure<Unit> by extra

applyNativeModulesSettingsGradle(settings, "$rootDir/frontend")

include(
    ":android:app",
    ":android:base",
    ":android:components:auth",
    ":android:features:auth",
    ":libraries:api",
    ":libraries:auth:infra",
    ":libraries:auth:domain:usecase:requestappcredential",
    ":libraries:auth:domain:usecase:requestaccesstoken"
)

pluginManagement {
    repositories {
        google()
        jcenter()
        gradlePluginPortal()
        mavenLocal()
        maven("https://kotlin.bintray.com/kotlinx")
    }

    resolutionStrategy {
        eachPlugin {
            when {
                requested.id.id.startsWith("org.jetbrains.kotlin") -> useModule(Libraries.GradlePlugin.kotlin)
                requested.id.id.startsWith("com.android") -> useModule(Libraries.GradlePlugin.android)
                requested.id.id == "kotlinx-serialization" -> useModule(Libraries.GradlePlugin.kotlinSerialization)
            }
        }
    }
}
