package id44.mizuki.libraries.api.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CardJson(
    val url: String,
    val title: String,
    val description: String,
    val image: String? = null,
    val type: String,
    @SerialName("author_name")
    val authorName: String? = null,
    @SerialName("author_url")
    val authorUrl: String? = null,
    @SerialName("provider_name")
    val providerName: String? = null,
    @SerialName("provider_url")
    val providerUrl: String? = null,
    val html: String? = null,
    val width: Int,
    val height: Int
)
