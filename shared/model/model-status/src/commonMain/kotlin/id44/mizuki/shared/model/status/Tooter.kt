package id44.mizuki.shared.model.status

import kotlinx.serialization.Serializable

@Serializable
data class Tooter(
    val id: String,
    val username: String,
    val displayName: String,
    val url: String,
    val avatar: String,
    val avatarStatic: String
)
