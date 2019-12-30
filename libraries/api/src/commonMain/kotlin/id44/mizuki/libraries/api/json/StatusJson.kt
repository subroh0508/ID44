package id44.mizuki.libraries.api.json

import id44.mizuki.libraries.api.JsonData
import id44.mizuki.libraries.api.RawJson
import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.json.account.EmojiJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json

@Serializable
data class StatusJson(
    override val raw: RawJson
) : JsonData {
    constructor(json: String) : this(Json.nonstrict.parse(RawJson.serializer(), json))

    val id: String by raw
    val uri: String by raw
    val url: String? by raw
    val account: AccountJson by RawJson.Delegate(::AccountJson)
    val inReplyToId: String? by raw
    val inReplyToAccountId: String? by raw
    val reblog: StatusJson? by RawJson.NullableDelegate(::StatusJson)
    val content: String by raw
    val createdAt: String by raw
    val emojis: List<EmojiJson> by RawJson.ListDelegate(::EmojiJson)
    val repliesCount: Int by raw
    val reblogsCount: Int by raw
    val favouritesCount: Int by raw
    val reblogged: Boolean? by raw
    val favourited: Boolean? by raw
    val muted: Boolean? by raw
    val sensitive: Boolean by raw
    val spoilerText: String by raw
    val visibility: String by raw
    val mediaAttachments: List<AttachmentJson> by RawJson.ListDelegate(::AttachmentJson)
    val mentions: List<MentionJson> by RawJson.ListDelegate(::MentionJson)
    val tags: List<TagJson> by RawJson.ListDelegate(::TagJson)
    val card: CardJson? by RawJson.NullableDelegate(::CardJson)
    val poll: PollJson? by RawJson.NullableDelegate(::PollJson)
    val application: ApplicationJson by RawJson.Delegate(::ApplicationJson)
    val language: String? by raw
    val pinned: Boolean? by raw
}
