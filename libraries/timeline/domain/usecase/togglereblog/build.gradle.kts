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
                implementation(project(":libraries:auth:infra"))
                implementation(project(":libraries:timeline:domain:timeline-entity"))
                implementation(project(":libraries:timeline:domain:timeline-valueobject"))
                implementation(project(":libraries:timeline:infra"))

                implementation(Libraries.Kotlin.stdlibCommon)

                implementation(Libraries.Coroutines.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":android:base"))

                implementation(Libraries.Kotlin.stdlibJvm)

                implementation(Libraries.Coroutines.android)

                implementation(Libraries.Dagger.core)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.stdlibJs)

                implementation(Libraries.Coroutines.js)
            }
        }
    }
}

withGroovyBuilder {
    "dependencies" {
        "kapt"(Libraries.Dagger.compiler)
    }
}