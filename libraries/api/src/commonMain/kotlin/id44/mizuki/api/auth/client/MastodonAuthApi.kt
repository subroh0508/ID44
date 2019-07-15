package id44.mizuki.api.auth.client

import id44.mizuki.api.model.AccessToken

interface MastodonAuthApi {
    fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        scope: String
    ): String

    suspend fun requestAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): AccessToken
}