package id44.mizuki.api.json.account

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson

data class SourceJson(
    override val raw: RawJson
) : JsonData {
    val privacy: String by raw
    val sensitive: Boolean by raw
    val note: String by raw
    val language: String? by raw
    val fields: List<FieldJson>? by RawJson.NullableListDelegate(::FieldJson)
}
