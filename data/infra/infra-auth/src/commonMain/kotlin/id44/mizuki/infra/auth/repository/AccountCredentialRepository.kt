package id44.mizuki.infra.auth.repository

import id44.mizuki.shared.model.auth.ClientId
import id44.mizuki.shared.model.auth.ClientSecret
import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri

interface AccountCredentialRepository {
    fun buildAuthorizeUrl(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri
    ): Uri

    suspend fun fetchAccessToken(
        hostName: HostName,
        clientId: ClientId,
        clientSecret: ClientSecret,
        redirectUri: Uri,
        code: String
    ): AccessToken

    suspend fun saveOwnAccount(
        hostName: HostName,
        accessToken: AccessToken
    )
}
