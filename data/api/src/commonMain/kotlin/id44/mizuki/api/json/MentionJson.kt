package id44.mizuki.api.json

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.Serializable

@Serializable
data class MentionJson(
    override val raw: RawJson
) : JsonData {
    val id: String by raw
    val username: String by raw
    val acct: String by raw
    val url: String by raw
}
