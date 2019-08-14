package id44.mizuki.libraries.api.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MetaJson(
    val focus: Focus? = null,
    val small: InfoJson? = null,
    val original: InfoJson? = null
) {
    @Serializable
    data class Focus(val x: Float, val y: Float)

    @Serializable
    data class InfoJson(
        val width: Int,
        val height: Int,
        val size: String? = null,
        val aspect: Double? = null,
        @SerialName("frame_rate")
        val frameRate: String? = null,
        val duration: Float? = null,
        val bitrate: Int? = null
    )
}
