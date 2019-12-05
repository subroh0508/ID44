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
                implementation(project(":libraries:api"))
                implementation(project(":libraries:auth:infra"))
                implementation(project(":libraries:auth:domain:usecase:switchaccesstoken"))
                implementation(project(":libraries:account:infra"))
                implementation(project(":libraries:account:domain:usecase:fetchownaccounts"))
                implementation(project(":libraries:account:domain:entity"))
                implementation(project(":libraries:timeline:infra"))
                implementation(project(":libraries:timeline:domain:usecase:subscribe"))
                implementation(project(":libraries:timeline:domain:usecase:unsubscribe"))
                implementation(project(":libraries:timeline:domain:entity"))
                implementation(project(":libraries:timeline:domain:valueobject"))

                implementation(Libraries.Kotlin.stdlibCommon)
                implementation(Libraries.Kotlin.serializationCommon)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Ktor.clientCommon)
                implementation(Libraries.Ktor.jsonCommon)
                implementation(Libraries.Ktor.serializationCommon)

                implementation(Libraries.Klock.common)

                implementation(Libraries.reactNative)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":android:base"))
                implementation(project(":android:components:core"))
                implementation(project(":react-native-vector-icons"))
                implementation(project(":react-native-gesture-handler"))
                implementation(project(":react-native-reanimated"))

                implementation(Libraries.Kotlin.stdlibJvm)
                implementation(Libraries.Kotlin.reflect)

                implementation(Libraries.Klock.android)

                implementation(Libraries.Ktor.clientAndroid)
                implementation(Libraries.Ktor.loggingJvm)
                implementation(Libraries.Ktor.jsonJvm)
                implementation(Libraries.Ktor.serializationJvm)

                implementation(Libraries.Okhttp3.client)
                implementation(Libraries.Okhttp3.loggingIntercerptor)

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
