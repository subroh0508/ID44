plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

androidMPP()

kotlin {
    kotlinMPPShared()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libraries.Kotlin.common)
                implementation(Libraries.Serialization.common)
                implementation(Libraries.Serialization.Properties.common)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.jvm)
                implementation(Libraries.Serialization.jvm)
                implementation(Libraries.Serialization.Properties.jvm)

                implementation(Libraries.reactNative)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.js)
                implementation(Libraries.Serialization.js)
                implementation(Libraries.Serialization.Properties.js)
            }
        }

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}

