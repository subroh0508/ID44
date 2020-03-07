package id44.mizuki.api.json.account

import id44.mizuki.api.JsonData
import id44.mizuki.api.RawJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountJson(
    override val raw: RawJson
) : JsonData {
    val id: String by raw
    val username: String by raw
    val acct: String by raw
    val displayName: String by raw
    val locked: Boolean by raw
    val createdAt: String by raw
    val followersCount: Int by raw
    val followingCount: Int by raw
    val statusesCount: Int by raw
    val note: String by raw
    val url: String by raw
    val avatar: String by raw
    val avatarStatic: String by raw
    val header: String by raw
    val headerStatic: String by raw
    val emojis: List<EmojiJson>? by RawJson.NullableListDelegate(::EmojiJson)
    val fields: List<FieldJson>? by RawJson.NullableListDelegate(::FieldJson)
    val bot: Boolean by raw
    val moved: AccountJson? by RawJson.NullableDelegate(::AccountJson)
}
