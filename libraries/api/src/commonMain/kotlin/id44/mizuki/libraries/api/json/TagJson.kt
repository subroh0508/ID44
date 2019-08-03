package id44.mizuki.libraries.api.json

import kotlinx.serialization.Serializable

@Serializable
data class TagJson(
    val name: String,
    val url: String,
    val history: List<HistoryJson>? = null
)
