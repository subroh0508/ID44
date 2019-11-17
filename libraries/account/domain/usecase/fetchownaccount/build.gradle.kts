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
                implementation(project(":libraries:shared"))
                implementation(project(":libraries:account:infra"))
                implementation(project(":libraries:account:domain:entity"))
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
    }
}

withGroovyBuilder {
    "dependencies" {
        "kapt"(Libraries.Dagger.compiler)
    }
}
