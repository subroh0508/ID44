package id44.mizuki.libraries.api.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiJson(
    val shortcode: String,
    @SerialName("static_url")
    val staticUrl: String,
    val url: String,
    @SerialName("visible_in_picker")
    val visibleInPicker: Boolean
)
