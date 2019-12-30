package id44.mizuki.libraries.api.json

import id44.mizuki.libraries.api.JsonData
import id44.mizuki.libraries.api.RawJson
import kotlinx.serialization.Serializable

@Serializable
data class ApplicationJson(
    override val raw: RawJson
) : JsonData {
    val name: String by raw
    val website: String? by raw
}
