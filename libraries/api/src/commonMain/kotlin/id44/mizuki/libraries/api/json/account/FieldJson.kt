package id44.mizuki.libraries.api.json.account

import id44.mizuki.libraries.api.JsonData
import id44.mizuki.libraries.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldJson(
    override val raw: RawJson
) : JsonData {
    val name: String by raw
    val value: String by raw
    val verifiedAt: String? by raw
}

