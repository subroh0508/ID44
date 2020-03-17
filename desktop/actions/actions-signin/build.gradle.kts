plugins {
    kotlin("js")
    kotlin("plugin.serialization")
}

kotlin {
    target { nodejs() }

    sourceSets {
        val main by getting {
            dependencies {
                implementation(project(":shared:util"))
                implementation(project(":shared:model:model-auth"))
                implementation(project(":shared:presentation:presentation-signin"))
                implementation(project(":data:infra:infra-auth"))
                implementation(project(":domain:signin:usecase-signin"))

                implementation(Libraries.Kotlin.js)
                implementation(Libraries.Serialization.js)

                implementation(Libraries.Coroutines.js)

                implementation(Libraries.Ktor.clientJs)
                implementation(Libraries.Ktor.jsonJs)
                implementation(Libraries.Ktor.serializationJs)

                implementation(Libraries.Klock.js)

                implementation(Libraries.Kodein.js)

                implementation(npm("text-encoding", "0.7.0"))
            }
        }
    }
}
