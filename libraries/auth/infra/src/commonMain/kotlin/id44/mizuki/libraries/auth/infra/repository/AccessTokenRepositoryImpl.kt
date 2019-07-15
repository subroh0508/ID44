package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AccessTokenStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AccessTokenRepositoryImpl(
    private val authApi: MastodonAuthApi,
    private val localStore: AccessTokenStore
) : AccessTokenRepository {
    override fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String
    ): String = authApi.buildAuthorizeUrl(hostName, clientId, clientSecret)

    override suspend fun fetchAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        code: String
    ): String = withContext(Dispatchers.Default) {
        authApi.requestAccessToken(hostName, clientId, clientSecret, code).accessToken
    }

    override fun cacheAccessToken(hostName: String, token: String) {
        localStore.cacheAccessToken(hostName, token)
    }

    override fun clearAccessToken(hostName: String) {
        localStore.clearAccessToken(hostName)
    }
}
