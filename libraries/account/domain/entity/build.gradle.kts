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
                implementation(project(":libraries:shared"))
                implementation(Libraries.Kotlin.stdlibCommon)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJvm)
            }
        }
    }
}

