plugins {
    kotlin("multiplatform")
    id("com.android.library")
    kotlin("plugin.serialization")
}

androidMPP()

kotlin {
    kotlinMPPShared()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":data:api"))
                implementation(project(":shared:util"))
                implementation(project(":shared:model:model-auth"))
                implementation(Libraries.Kotlin.common)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Kodein.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.jvm)

                implementation(Libraries.Coroutines.jvm)
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
