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
                implementation(project(":bridges:bridges-shared"))
                implementation(project(":shared:util"))
                implementation(project(":data:api"))
                implementation(project(":data:infra:infra-auth"))
                implementation(project(":shared:model:model-auth"))
                implementation(project(":domain:signin:usecase-signin"))

                implementation(Libraries.Kotlin.common)
                implementation(Libraries.Serialization.common)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Ktor.clientCommon)
                implementation(Libraries.Ktor.jsonCommon)
                implementation(Libraries.Ktor.serializationCommon)

                implementation(Libraries.Klock.common)

                implementation(Libraries.Kodein.common)
            }
        }

        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(project(":libraries:reactnativesupport"))
                implementation(project(":android:base"))
                implementation(project(":react-native-vector-icons"))
                implementation(project(":react-native-gesture-handler"))
                implementation(project(":react-native-reanimated"))

                implementation(Libraries.Kotlin.jvm)
                implementation(Libraries.Kotlin.reflect)
                implementation(Libraries.Serialization.jvm)

                implementation(Libraries.Klock.android)

                implementation(Libraries.Ktor.clientAndroid)
                implementation(Libraries.Ktor.loggingJvm)
                implementation(Libraries.Ktor.jsonJvm)
                implementation(Libraries.Ktor.serializationJvm)

                implementation(Libraries.Okhttp3.client)
                implementation(Libraries.Okhttp3.loggingIntercerptor)

                implementation(Libraries.reactNative)

                implementation(Libraries.Kodein.jvm)
            }
        }

        val jsMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(Libraries.Kotlin.js)
                implementation(Libraries.Serialization.js)

                implementation(Libraries.Klock.js)

                implementation(Libraries.Ktor.clientJs)
                implementation(Libraries.Ktor.jsonJs)
                implementation(Libraries.Ktor.serializationJs)
            }
        }
    }
}
