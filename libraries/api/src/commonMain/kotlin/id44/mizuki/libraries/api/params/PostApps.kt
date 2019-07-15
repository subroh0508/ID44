package id44.mizuki.libraries.api.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class PostApps {
    @Serializable
    data class Request(
        @SerialName("client_name")
        val clientName: String,
        @SerialName("redirect_uris")
        val redirectUris: String,
        val scopes: String,
        val website: String
    )
}