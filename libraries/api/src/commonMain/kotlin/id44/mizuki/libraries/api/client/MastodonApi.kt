package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.json.AccountJson
import id44.mizuki.libraries.api.params.VerifyAppCredential

interface MastodonApi {
    suspend fun verifyAppCredential(): VerifyAppCredential.Response

    suspend fun getAccount(id: String): AccountJson
}
