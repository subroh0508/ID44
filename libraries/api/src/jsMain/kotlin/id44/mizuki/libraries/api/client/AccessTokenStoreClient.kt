package id44.mizuki.libraries.api.client

actual class AccessTokenStoreClient : AccessTokenStore {
    override fun cacheAccessToken(id: String, token: String) = Unit
    override fun clearAccessToken(id: String) = Unit
    override fun getAccessToken(id: String): String? = null
    override fun getAllAuthenticatedAccountIds(): List<String> = listOf()
}
