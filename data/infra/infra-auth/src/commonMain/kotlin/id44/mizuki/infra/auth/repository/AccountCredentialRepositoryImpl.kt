package id44.mizuki.infra.auth.repository

import id44.mizuki.api.auth.client.MastodonAuthApi
import id44.mizuki.api.client.AccessTokenStore
import id44.mizuki.api.client.LocalCacheStore
import id44.mizuki.shared.model.auth.ClientId
import id44.mizuki.shared.model.auth.ClientSecret
import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri

internal class AccountCredentialRepositoryImpl(
    private val authApi: MastodonAuthApi,
    private val authLocalStore: AccessTokenStore,
    private val localStore: LocalCacheStore
) : AccountCredentialRepository {
    override fun buildAuthorizeUrl(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri
    ): Uri = Uri(authApi.buildAuthorizeUrl(
        hostName.value,
        clientId.value,
        clientSecret.value,
        redirectUri.value
    ))

    override suspend fun fetchAccessToken(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri,
        code: String
    ): AccessToken = AccessToken(
        authApi.requestAccessToken(
            hostName.value,
            clientId.value,
            clientSecret.value,
            redirectUri.value,
            code
        ).accessToken
    )

    override suspend fun saveOwnAccount(hostName: HostName, accessToken: AccessToken) {
        val res = authApi.getVerifyAccountsCredentials(hostName.value, accessToken.value)

        localStore.cacheVerifyAccountsCredential(hostName.value, res)
        authLocalStore.cacheAccessToken(res.id, accessToken.value)
        localStore.cacheNowVerifyAccountsCredential(hostName.value, res)
    }
}
