package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardJson(
    override val raw: RawJson
) : JsonData {
    val url: String by raw
    val title: String by raw
    val description: String by raw
    val image: String? by raw
    val type: String by raw
    val authorName: String? by raw
    val authorUrl: String? by raw
    val providerName: String? by raw
    val providerUrl: String? by raw
    val html: String? by raw
    val width: Int by raw
    val height: Int by raw
}
