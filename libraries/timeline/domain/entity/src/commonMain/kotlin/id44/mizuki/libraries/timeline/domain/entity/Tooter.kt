package id44.mizuki.libraries.timeline.domain.entity

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
