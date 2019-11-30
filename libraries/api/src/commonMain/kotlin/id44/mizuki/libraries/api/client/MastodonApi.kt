package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.shared.valueobject.HostName

interface MastodonApi {
    val host: HostName

    suspend fun getVerifyAccountsCredential(): GetAccountsVerifyCredential.Response

    suspend fun getAccount(id: String): AccountJson
}
