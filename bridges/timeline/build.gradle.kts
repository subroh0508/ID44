plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
}

androidMPP()

kotlin {
    android()

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":bridges:bridges-shared"))
                implementation(project(":bridges:bridges-auth"))
                implementation(project(":shared:util"))
                implementation(project(":libraries:reactnativesupport"))
                implementation(project(":data:api"))
                implementation(project(":data:infra:infra-auth"))
                implementation(project(":data:infra:infra-account"))
                implementation(project(":shared:model:model-account"))
                implementation(project(":data:infra:infra-status"))
                implementation(project(":domain:timeline:usecase-timeline"))
                implementation(project(":shared:model:model-status"))

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
                implementation(project(":android:base"))
                implementation(project(":react-native-vector-icons"))
                implementation(project(":react-native-gesture-handler"))
                implementation(project(":react-native-reanimated"))
                implementation(project(":react-native-fast-image"))

                implementation(Libraries.Kotlin.jvm)
                implementation(Libraries.Serialization.jvm)
                implementation(Libraries.Kotlin.reflect)

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
    }
}
