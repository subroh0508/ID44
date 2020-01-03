package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.json.StatusJson
import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.json.enums.StatusVisibilityType
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.shared.valueobject.HostName

interface MastodonApi {
    val host: HostName

    suspend fun getVerifyAccountsCredential(): GetAccountsVerifyCredential.Response
    suspend fun getAccount(id: String): AccountJson

    suspend fun getTimelinesPublic(maxId: String? = null, limit: Int = 20): List<StatusJson>
    suspend fun getTimelinesLocal(maxId: String? = null, limit: Int = 20): List<StatusJson>

    suspend fun postStatus(
        status: String?, mediaIds: List<String> = emptyList(),
        inReplyToId: String? = null,
        sensitive: Boolean = false, spoilerText: String? = null, visibility: StatusVisibilityType = StatusVisibilityType.public
    ) : StatusJson
}
