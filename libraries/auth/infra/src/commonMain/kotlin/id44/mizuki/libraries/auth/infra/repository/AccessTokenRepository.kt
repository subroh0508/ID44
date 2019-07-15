package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.model.AccessToken

interface AccessTokenRepository {
    fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        scope: String
    ): String

    suspend fun fetchAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken

    fun cacheAccessToken(hostName: String, token: AccessToken)

    fun clearAccessToken(hostName: String)
}
