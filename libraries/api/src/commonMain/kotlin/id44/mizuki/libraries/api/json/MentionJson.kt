package id44.mizuki.libraries.api.json

import kotlinx.serialization.Serializable

@Serializable
data class MentionJson(
    val id: String,
    val username: String,
    val acct: String,
    val url: String
)
