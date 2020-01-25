package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.auth.domain.valueobject.ClientId
import id44.mizuki.libraries.auth.domain.valueobject.ClientSecret
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.libraries.shared.valueobject.parseToUri

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
    ): Uri = authApi.buildAuthorizeUrl(
        hostName.value,
        clientId.value,
        clientSecret.value,
        redirectUri.toString()
    ).parseToUri()

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
        localStore.cacheNowVerifyAccountsCredential(hostName.value, res)
    }
}
