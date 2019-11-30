package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.Credentials
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.auth.domain.valueobject.ClientId
import id44.mizuki.libraries.auth.domain.valueobject.ClientSecret
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri

internal actual class AccountCredentialRepositoryImpl(
    private val authApi: MastodonAuthApi,
    private val authLocalStore: AccessTokenStore,
    private val localStore: LocalCacheStore
) : AccountCredentialRepository {
    override fun buildAuthorizeUrl(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri
    ): Uri = Uri.parse(
        authApi.buildAuthorizeUrl(hostName.value, clientId.value, clientSecret.value, redirectUri.toString())
    )

    override suspend fun fetchAccessToken(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri,
        code: String
    ): AccessToken = AccessToken(
        authApi.requestAccessToken(hostName.value, clientId.value, clientSecret.value, redirectUri.toString(), code).accessToken
    )

    override suspend fun saveOwnAccount(hostName: HostName, accessToken: AccessToken) {
        val res = authApi.getVerifyAccountsCredentials(hostName.value, accessToken.value)

        localStore.cacheVerifyAccountsCredential(hostName.value, res)
        authLocalStore.cacheAccessToken(res.id, accessToken.value)

        Credentials.setNowCredentials(hostName, accessToken)
    }
}
