package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.json.AccountJson
import id44.mizuki.libraries.api.params.GetAppsVerifyCredential

interface MastodonApi {
    suspend fun verifyAppCredential(hostName: String): GetAppsVerifyCredential.Response

    suspend fun getAccount(hostName: String, id: String): AccountJson
}
