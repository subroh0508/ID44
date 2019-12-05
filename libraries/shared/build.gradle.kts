plugins {
    kotlin("multiplatform")
    id("com.android.library")
}

androidMPP()

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(Libraries.Kotlin.stdlibCommon)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJvm)

                implementation(Libraries.reactNative)
            }
        }

        all {
            languageSettings.enableLanguageFeature("InlineClasses")
        }
    }
}

