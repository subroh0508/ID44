package id44.mizuki.api.streaming.json

import kotlinx.serialization.Serializable

@Serializable
data class StreamingEventJson(
    val event: String,
    val payload: String? = null
)
