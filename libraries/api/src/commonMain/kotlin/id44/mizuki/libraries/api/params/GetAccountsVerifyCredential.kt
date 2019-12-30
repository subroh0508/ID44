
package id44.mizuki.libraries.api.params

import id44.mizuki.libraries.api.JsonData
import id44.mizuki.libraries.api.RawJson
import id44.mizuki.libraries.api.json.account.*
import kotlinx.serialization.*

abstract class GetAccountsVerifyCredential {
    @Serializable
    data class Response(
        override val raw: RawJson
    ) : JsonData {
        val id: String by raw
        val hostName: String by raw
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
        val bot: Boolean by raw
        val source: SourceJson by RawJson.Delegate(SourceJson::class)
    }

    @Serializable
    data class Cache(
        val hostName: String,
        val response: Response
    )
}
