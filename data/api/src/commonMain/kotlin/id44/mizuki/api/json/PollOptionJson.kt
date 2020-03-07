package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollOptionJson(
    override val raw: RawJson
) : JsonData {
    val title: String by raw
    val votesCount: Int? by raw
}
