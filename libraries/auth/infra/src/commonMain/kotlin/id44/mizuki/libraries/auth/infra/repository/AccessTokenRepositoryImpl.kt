package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.LocalCacheStore

internal class AccessTokenRepositoryImpl(
    private val authApi: MastodonAuthApi,
    private val authLocalStore: AccessTokenStore,
    private val localStore: LocalCacheStore
) : AccessTokenRepository {
    override fun getAuthenticatedHostNames(): List<String> = authLocalStore.getAuthenticatedHostNames()

    override fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ): String = authApi.buildAuthorizeUrl(hostName, clientId, clientSecret, redirectUri)

    override suspend fun fetchAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): String = authApi.requestAccessToken(hostName, clientId, clientSecret, redirectUri, code).accessToken

    override fun cacheAccessToken(
        hostName: String,
        token: String
    ) = authLocalStore.cacheAccessToken(hostName, token)

    override fun clearAccessToken(
        hostName: String
    ) = authLocalStore.clearAccessToken(hostName)

    override suspend fun saveOwnAccount(
        hostName: String,
        accessToken: String
    ) = localStore.cacheVerifyAppCredential(
        hostName,
        authApi.getVerifyAppCredentials(hostName, accessToken)
    )
}
