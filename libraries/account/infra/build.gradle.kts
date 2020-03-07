plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

androidMPP()

kotlin {
    kotlinMPPShared()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:api"))
                implementation(project(":libraries:shared"))
                implementation(project(":libraries:account:domain:account-entity"))

                implementation(Libraries.Kotlin.common)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Kodein.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":android:base"))

                implementation(Libraries.Kotlin.jvm)

                implementation(Libraries.Coroutines.android)

                implementation(Libraries.Jetpack.ktx)

                implementation(Libraries.Kodein.jvm)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.js)

                implementation(Libraries.Coroutines.js)

                implementation(Libraries.Kodein.js)
            }
        }
    }
}

