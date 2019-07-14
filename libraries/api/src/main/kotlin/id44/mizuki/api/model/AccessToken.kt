package id44.mizuki.api.model

data class AccessToken(
    val accessToken: String,
    val tokenType: String,
    val scope: String,
    val createdAt: Long
)
