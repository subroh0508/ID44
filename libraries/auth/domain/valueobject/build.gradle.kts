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
                implementation(Libraries.Kotlin.stdlibCommon)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJvm)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJs)
            }
        }
    }
}

