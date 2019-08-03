package id44.mizuki.libraries.api.json

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollOptionJson(
    val title: String,
    @SerialName("votes_count")
    val votesCount: Int? = null
)
