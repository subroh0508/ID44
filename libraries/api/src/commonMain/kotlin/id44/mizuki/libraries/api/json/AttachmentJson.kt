package id44.mizuki.libraries.api.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AttachmentJson(
    val id: String,
    val type: String,
    val url: String,
    @SerialName("remote_url")
    val remoteUrl: String? = null,
    @SerialName("preview_url")
    val previewUrl: String,
    @SerialName("text_url")
    val textUrl: String? = null,
    //val meta: String? = null,
    val description: String? = null
)
