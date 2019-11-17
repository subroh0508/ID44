package id44.mizuki.libraries.api.json.account

import id44.mizuki.libraries.api.json.FieldJson
import kotlinx.serialization.Serializable

@Serializable
data class SourceJson(
    val privacy: String,
    val sensitive: Boolean,
    val note: String,
    val language: String? = null,
    val fields: List<FieldJson>? = null
)
