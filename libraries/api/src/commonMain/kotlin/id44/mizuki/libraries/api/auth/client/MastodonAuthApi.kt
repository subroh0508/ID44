package id44.mizuki.libraries.api.auth.client

import id44.mizuki.libraries.api.model.AccessToken
import id44.mizuki.libraries.api.model.AppCredential

interface MastodonAuthApi {
    suspend fun requestAppCredential(
        hostName: String
    ): AppCredential

    fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String
    ): String

    suspend fun requestAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken
}