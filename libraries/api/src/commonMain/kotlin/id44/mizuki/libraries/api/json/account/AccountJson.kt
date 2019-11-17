package id44.mizuki.libraries.api.json.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountJson(
    override val id: String,
    override val username: String,
    override val acct: String,
    @SerialName("display_name")
    override val displayName: String,
    override val locked: Boolean,
    @SerialName("created_at")
    override val createdAt: String,
    @SerialName("followers_count")
    override val followersCount: Int,
    @SerialName("following_count")
    override val followingCount: Int,
    @SerialName("statuses_count")
    override val statusesCount: Int,
    override val note: String,
    override val url: String,
    override val avatar: String,
    @SerialName("avatar_static")
    override val avatarStatic: String,
    override val header: String,
    @SerialName("header_static")
    override val headerStatic: String,
    override val emojis: List<EmojiJson>? = null,
    override val fields: List<FieldJson>? = null,
    override val bot: Boolean,
    val moved: AccountJson? = null
) : AccountJsonFacade
