package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.auth.params.GetAccount

interface MastodonApi {
    suspend fun verifyAppCredential(): Boolean

    suspend fun getAccount(id: String): GetAccount.Response
}
