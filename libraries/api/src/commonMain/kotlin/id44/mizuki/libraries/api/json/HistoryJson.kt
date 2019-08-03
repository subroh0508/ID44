package id44.mizuki.libraries.api.json

import kotlinx.serialization.Serializable

@Serializable
data class HistoryJson(
    val day: String,
    val uses: Int,
    val accounts: Int
)
