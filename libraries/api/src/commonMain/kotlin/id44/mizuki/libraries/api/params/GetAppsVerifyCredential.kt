package id44.mizuki.libraries.api.params

import id44.mizuki.libraries.api.json.AccountJson
import id44.mizuki.libraries.api.json.EmojiJson
import id44.mizuki.libraries.api.json.FieldJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class GetAppsVerifyCredential {
    @Serializable
    data class Response(
        val id: String,
        val username: String,
        val acct: String,
        @SerialName("display_name")
        val displayName: String,
        val locked: Boolean,
        @SerialName("created_at")
        val createdAt: String,
        @SerialName("followers_count")
        val followersCount: Int,
        @SerialName("following_count")
        val followingCount: Int,
        @SerialName("statuses_count")
        val statusesCount: Int,
        val note: String,
        val url: String,
        val avatar: String,
        @SerialName("avatar_static")
        val avatarStatic: String,
        val header: String,
        @SerialName("header_static")
        val headerStatic: String,
        val emojis: List<EmojiJson>? = null,
        val moved: AccountJson? = null,
        val fields: List<FieldJson>? = null,
        val bot: Boolean,
        val privacy: String?,
        val sensitive: String?,
        val language: String?
    )

    @Serializable
    data class Cache(
        val hostName: String,
        val response: Response
    )
}
