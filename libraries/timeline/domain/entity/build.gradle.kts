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
                implementation(project(":libraries:timeline:domain:timeline-valueobject"))

                implementation(Libraries.Kotlin.stdlibCommon)
                implementation(Libraries.Kotlin.serializationCommon)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJvm)
                implementation(Libraries.Kotlin.serializationJvm)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJs)
                implementation(Libraries.Kotlin.serializationJs)
            }
        }

    }
}

