package id44.mizuki.libraries.api.client

interface AccessTokenStore {
    fun getAccessToken(hostName: String): String

    fun cacheAccessToken(hostName: String, token: String)

    fun clearAccessToken(hostName: String)
}
