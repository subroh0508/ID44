package id44.mizuki.libraries.api.json

import com.soywiz.klock.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class StatusJson(
    val id: String,
    val uri: String,
    val url: String? = null,
    val account: AccountJson,
    @SerialName("in_reply_to_id")
    val inReplyToId: String? = null,
    @SerialName("in_reply_to_account_ud")
    val inReplyToAccountId: String? = null,
    val reblog: StatusJson? = null,
    val content: String,
    @SerialName("created_at")
    val createdAt: DateTime,
    val emojis: List<EmojiJson>,
    @SerialName("replies_count")
    val repliesCount: Int,
    @SerialName("reblogs_count")
    val reblogsCount: Int,
    @SerialName("favourites_count")
    val favouritesCount: Int,
    val reblogged: Boolean? = null,
    val favourited: Boolean? = null,
    val muted: Boolean? = null,
    val sensitive: Boolean,
    @SerialName("spoiler_text")
    val spoilerText: String,
    val visibility: String,
    @SerialName("media_attachments")
    val mediaAttachments: List<AttachmentJson>,
    val mentions: List<MentionJson>,
    val tags: List<TagJson>,
    val card: CardJson? = null,
    val poll: PollJson? = null,
    val application: ApplicationJson,
    val language: String? = null,
    val pinned: Boolean? = null
)
