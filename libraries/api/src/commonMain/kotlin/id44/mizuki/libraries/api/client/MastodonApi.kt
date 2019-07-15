package id44.mizuki.libraries.api.client

interface MastodonApi {
    suspend fun verifyAppCredential(): Boolean
}
