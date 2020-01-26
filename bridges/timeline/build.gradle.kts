plugins {
    kotlin("multiplatform")
    id("com.android.library")
    id("kotlinx-serialization")
    kotlin("kapt")
}

androidMPP()

kotlin {
    android {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                implementation(project(":bridges:shared"))
                implementation(project(":bridges:auth"))
                implementation(project(":libraries:shared"))
                implementation(project(":libraries:reactnativesupport"))
                implementation(project(":libraries:api"))
                implementation(project(":libraries:auth:infra"))
                implementation(project(":libraries:auth:domain:usecase:switchaccesstoken"))
                implementation(project(":libraries:account:infra"))
                implementation(project(":libraries:account:domain:usecase:fetchownaccount"))
                implementation(project(":libraries:account:domain:usecase:fetchownaccounts"))
                implementation(project(":libraries:account:domain:account-entity"))
                implementation(project(":libraries:timeline:infra"))
                implementation(project(":libraries:timeline:domain:usecase:fetchstatuses"))
                implementation(project(":libraries:timeline:domain:usecase:subscribe"))
                implementation(project(":libraries:timeline:domain:usecase:unsubscribe"))
                implementation(project(":libraries:timeline:domain:usecase:submitstatus"))
                implementation(project(":libraries:timeline:domain:usecase:togglefavourite"))
                implementation(project(":libraries:timeline:domain:usecase:togglereblog"))
                implementation(project(":libraries:timeline:domain:timeline-entity"))
                implementation(project(":libraries:timeline:domain:timeline-valueobject"))

                implementation(Libraries.Kotlin.stdlibCommon)
                implementation(Libraries.Kotlin.serializationCommon)

                implementation(Libraries.Coroutines.common)

                implementation(Libraries.Ktor.clientCommon)
                implementation(Libraries.Ktor.jsonCommon)
                implementation(Libraries.Ktor.serializationCommon)

                implementation(Libraries.Klock.common)

                implementation(Libraries.Kodein.erasedCommon)
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
                implementation(project(":react-native-fast-image"))

                implementation(Libraries.Kotlin.stdlibJvm)
                implementation(Libraries.Kotlin.serializationJvm)
                implementation(Libraries.Kotlin.reflect)

                implementation(Libraries.Klock.android)

                implementation(Libraries.Ktor.clientAndroid)
                implementation(Libraries.Ktor.loggingJvm)
                implementation(Libraries.Ktor.jsonJvm)
                implementation(Libraries.Ktor.serializationJvm)

                implementation(Libraries.Okhttp3.client)
                implementation(Libraries.Okhttp3.loggingIntercerptor)

                implementation(Libraries.reactNative)

                implementation(Libraries.Kodein.genericJvm)
            }
        }
    }
}

withGroovyBuilder {
    "dependencies" {
        "kapt"(Libraries.Dagger.compiler)
    }
}
