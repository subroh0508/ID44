package id44.mizuki.auth.infra

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class Response {
    @Serializable
    data class AccessToken(
        @SerialName("access_token")
        val accessToken: String,
        @SerialName("token_type")
        val tokenType: String,
        val scope: String,
        @SerialName("created_at")
        val createdAt: Long
    )
}