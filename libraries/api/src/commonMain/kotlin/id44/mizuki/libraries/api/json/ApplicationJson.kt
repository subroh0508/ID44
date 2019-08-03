package id44.mizuki.libraries.api.json

import kotlinx.serialization.Serializable

@Serializable
data class ApplicationJson(
    val name: String,
    val website: String? = null
)
