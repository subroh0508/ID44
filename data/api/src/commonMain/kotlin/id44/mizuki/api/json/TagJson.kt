package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.Serializable

@Serializable
data class TagJson(
    override val raw: RawJson
) : JsonData {
    val name: String by raw
    val url: String by raw
    val history: List<HistoryJson>? by RawJson.NullableListDelegate(::HistoryJson)
}
