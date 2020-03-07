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
                implementation(project(":libraries:api"))
                implementation(project(":libraries:auth:auth-infra"))
                implementation(project(":libraries:auth:domain:usecase:switchaccesstoken"))
                implementation(project(":libraries:account:account-infra"))
                implementation(project(":libraries:account:domain:usecase:fetchownaccount"))
                implementation(project(":libraries:account:domain:usecase:fetchownaccounts"))
                implementation(project(":shared:model:model-account"))
                implementation(project(":libraries:timeline:timeline-infra"))
                implementation(project(":libraries:timeline:domain:usecase:fetchstatuses"))
                implementation(project(":libraries:timeline:domain:usecase:subscribe"))
                implementation(project(":libraries:timeline:domain:usecase:unsubscribe"))
                implementation(project(":libraries:timeline:domain:usecase:submitstatus"))
                implementation(project(":libraries:timeline:domain:usecase:togglefavourite"))
                implementation(project(":libraries:timeline:domain:usecase:togglereblog"))
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
