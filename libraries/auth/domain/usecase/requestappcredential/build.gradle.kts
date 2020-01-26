plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin("kapt")
}

androidMPP()

kotlin {
    kotlinMPPShared()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:shared"))
                implementation(project(":libraries:auth:domain:auth-valueobject"))
                implementation(project(":libraries:auth:infra"))
                implementation(Libraries.Kotlin.stdlibCommon)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Kodein.erasedCommon)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":android:base"))

                implementation(Libraries.Kotlin.stdlibJvm)

                implementation(Libraries.Coroutines.android)

                implementation(Libraries.Kodein.genericJvm)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJs)

                implementation(Libraries.Coroutines.js)

                implementation(Libraries.Kodein.erasedJs)
            }
        }
    }
}

withGroovyBuilder {
    "dependencies" {
        "kapt"(Libraries.Dagger.compiler)
    }
}

