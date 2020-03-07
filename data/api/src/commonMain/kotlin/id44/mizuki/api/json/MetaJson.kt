package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaJson(
    override val raw: RawJson
) : JsonData {
    val focus: Focus? by RawJson.NullableDelegate(::Focus)
    val small: InfoJson? by RawJson.NullableDelegate(::InfoJson)
    val original: InfoJson? by RawJson.NullableDelegate(::InfoJson)

    @Serializable
    data class Focus(override val raw: RawJson) : JsonData {
        val x: Float by raw
        val y: Float by raw
    }

    @Serializable
    data class InfoJson(override val raw: RawJson) : JsonData {
        val width: Int by raw
        val height: Int by raw
        val size: String? by raw
        val aspect: Double? by raw
        val frameRate: String? by raw
        val duration: Float? by raw
        val bitrate: Int? by raw
    }
}
