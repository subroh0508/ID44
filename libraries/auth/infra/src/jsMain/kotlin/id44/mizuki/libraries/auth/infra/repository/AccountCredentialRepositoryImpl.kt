package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.auth.domain.valueobject.ClientId
import id44.mizuki.libraries.auth.domain.valueobject.ClientSecret
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri

internal actual class AccountCredentialRepositoryImpl : AccountCredentialRepository {
    override fun buildAuthorizeUrl(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri
    ): Uri = UriImpl("dummy")

    override suspend fun fetchAccessToken(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri,
        code: String
    ): AccessToken = AccessToken("dummy")

    override suspend fun saveOwnAccount(hostName: HostName, accessToken: AccessToken) = Unit

    private class UriImpl(url: String) : Uri(url)
}
