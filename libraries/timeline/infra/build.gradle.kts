plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin("kapt")
}

androidMPP()

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":libraries:api"))
                implementation(project(":libraries:shared"))
                implementation(project(":libraries:timeline:domain:timeline-entity"))
                implementation(project(":libraries:timeline:domain:timeline-valueobject"))

                implementation(Libraries.Kotlin.stdlibCommon)
                implementation(Libraries.Kotlin.serializationCommon)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Klock.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":android:base"))

                implementation(Libraries.Kotlin.stdlibJvm)
                implementation(Libraries.Kotlin.serializationJvm)

                implementation(Libraries.Coroutines.android)

                implementation(Libraries.Jetpack.ktx)

                implementation(Libraries.Dagger.core)
            }
        }
    }
}

withGroovyBuilder {
    "dependencies" {
        "kapt"(Libraries.Dagger.compiler)
    }
}
