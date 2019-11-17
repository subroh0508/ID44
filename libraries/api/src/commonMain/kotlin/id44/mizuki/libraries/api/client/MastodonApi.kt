package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.json.AccountJson
import id44.mizuki.libraries.api.params.VerifyAppCredential

interface MastodonApi {
    suspend fun verifyAppCredential(hostName: String): VerifyAppCredential.Response

    suspend fun getAccount(hostName: String, id: String): AccountJson
}
