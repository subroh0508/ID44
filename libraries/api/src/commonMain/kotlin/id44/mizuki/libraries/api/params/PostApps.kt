package id44.mizuki.libraries.api.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class PostApps {
    @Serializable
    data class Request(
        @SerialName("client_name")
        val clientName: String,
        @SerialName("client_secret")
        val redirectUris: String,
        val scoped: String,
        val website: String
    )
}