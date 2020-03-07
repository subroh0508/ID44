package id44.mizuki.api.json.account

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmojiJson(
    override val raw: RawJson
) : JsonData {
    val shortcode: String by raw
    val staticUrl: String by raw
    val url: String by raw
    val visibleInPicker: Boolean by raw
}
