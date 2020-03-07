package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollJson(
    override val raw: RawJson
) : JsonData {
    val id: String by raw
    val expiresAt: String? by raw
    val expired: Boolean by raw
    val multiple: Boolean by raw
    val votesCount: Int by raw
    val options: List<PollOptionJson> by RawJson.ListDelegate(::PollOptionJson)
    val voted: Boolean by raw
}
