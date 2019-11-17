package id44.mizuki.libraries.api.auth.client

import id44.mizuki.libraries.api.auth.model.AccessToken
import id44.mizuki.libraries.api.auth.model.AppCredential
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential

interface MastodonAuthApi {
    suspend fun requestAppCredential(
        hostName: String,
        clientName: String,
        redirectUri: String
    ): AppCredential

    fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ): String

    suspend fun requestAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): AccessToken

    suspend fun getVerifyAccountsCredentials(
        hostName: String,
        accessToken: String
    ): GetAccountsVerifyCredential.Response
}
