include(
    ":android"
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
                requested.id.id.startsWith("com.android") -> useModule(Libraries.GradlePlugin.android)
                requested.id.id.startsWith("org.jetbrains.kotlin") -> useModule(Libraries.GradlePlugin.kotlin)
                requested.id.id == "kotlinx-serialization" -> useModule(Libraries.GradlePlugin.kotlinSerialization)
            }
        }
    }
}
