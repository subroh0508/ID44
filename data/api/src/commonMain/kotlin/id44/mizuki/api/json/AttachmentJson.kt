package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttachmentJson(
    override val raw: RawJson
) : JsonData {
    val id: String by raw
    val type: String by raw
    val url: String by raw
    val remoteUrl: String? by raw
    val previewUrl: String by raw
    val textUrl: String? by raw
    val meta: MetaJson? by RawJson.NullableDelegate(::MetaJson)
    val description: String? by raw
}
