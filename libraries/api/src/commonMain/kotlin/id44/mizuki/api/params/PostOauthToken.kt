package id44.mizuki.api.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class PostOauthToken {
    @Serializable
    data class Request(
        @SerialName("client_id")
        val clientId: String,
        @SerialName("client_secret")
        val clientSecret: String,
        @SerialName("redirect_uri")
        val redirectUri: String,
        val code: String,
        @SerialName("grant_type")
        val grantType: String = "authorization_code"
    )
}
