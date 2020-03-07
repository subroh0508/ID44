package id44.mizuki.api.client

import id44.mizuki.api.json.StatusJson
import id44.mizuki.api.json.account.AccountJson
import id44.mizuki.api.json.enums.StatusVisibilityType
import id44.mizuki.api.params.GetAccountsVerifyCredential
import id44.mizuki.shared.util.valueobject.HostName

interface MastodonApi {
    val host: HostName

    suspend fun getVerifyAccountsCredential(): GetAccountsVerifyCredential.Response
    suspend fun getAccount(id: String): AccountJson

    suspend fun getTimelinesGlobal(maxId: String? = null, limit: Int = 20): List<StatusJson>
    suspend fun getTimelinesLocal(maxId: String? = null, limit: Int = 20): List<StatusJson>
    suspend fun getTimelinesHome(maxId: String? = null, limit: Int = 20): List<StatusJson>

    suspend fun postStatus(
        status: String?, mediaIds: List<String> = emptyList(),
        inReplyToId: String? = null,
        sensitive: Boolean = false, spoilerText: String? = null, visibility: StatusVisibilityType = StatusVisibilityType.public
    ) : StatusJson

    suspend fun reblog(id: String): StatusJson
    suspend fun favourite(id: String): StatusJson
    suspend fun unreblog(id: String): StatusJson
    suspend fun unfavourite(id: String): StatusJson
}
