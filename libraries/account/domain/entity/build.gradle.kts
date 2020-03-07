plugins {
    kotlin("multiplatform")
    id("kotlinx-serialization")
    id("com.android.library")
}

androidMPP()

kotlin {
    kotlinMPPShared()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:shared"))
                implementation(Libraries.Kotlin.common)
                implementation(Libraries.Serialization.common)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.jvm)
                implementation(Libraries.Serialization.jvm)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.js)
                implementation(Libraries.Serialization.js)
            }
        }
    }
}

