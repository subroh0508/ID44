package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.Serializable

@Serializable
data class HistoryJson(
    override val raw: RawJson
) : JsonData {
    val day: String by raw
    val uses: Int by raw
    val accounts: Int by raw
}
