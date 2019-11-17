package id44.mizuki.libraries.auth.infra.repository

interface AccessTokenRepository {
    fun getAuthenticatedHostNames(): List<String>

    fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ): String

    suspend fun fetchAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): String

    fun cacheAccessToken(hostName: String, token: String)

    fun clearAccessToken(hostName: String)

    suspend fun saveOwnAccount(
        hostName: String,
        accessToken: String
    )
}
