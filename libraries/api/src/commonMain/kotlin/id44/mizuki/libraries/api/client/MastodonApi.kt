package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential

interface MastodonApi {
    suspend fun getVerifyAccountsCredential(hostName: String): GetAccountsVerifyCredential.Response

    suspend fun getAccount(hostName: String, id: String): AccountJson
}
