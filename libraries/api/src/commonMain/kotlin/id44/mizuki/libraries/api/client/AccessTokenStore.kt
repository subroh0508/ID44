package id44.mizuki.libraries.api.client

interface AccessTokenStore {
    fun getAllAuthenticatedAccountIds(): List<String>

    fun getAccessToken(id: String): String?

    fun cacheAccessToken(id: String, token: String)

    fun clearAccessToken(id: String)
}
