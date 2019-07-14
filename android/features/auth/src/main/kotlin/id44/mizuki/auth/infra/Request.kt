package id44.mizuki.auth.infra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class Request {
    @Serializable
    data class AccessToken(
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