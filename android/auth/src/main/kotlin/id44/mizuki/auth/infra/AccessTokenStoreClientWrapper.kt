package id44.mizuki.auth.infra

import id44.mizuki.libraries.api.client.AccessTokenStore

internal class AccessTokenStoreClientWrapper(
    private val accessTokenStore: AccessTokenStore
) {
    companion object {
        private const val AUTHENTICATED_HOST_NAMES = "AUTHENTICATED_HOST_NAMES"
    }

    fun getAuthenticatedHostNames(): List<String> = accessTokenStore.getAuthenticatedHostNames()

    fun clearAccessToken(hostName: String) = accessTokenStore.clearAccessToken(hostName)
}
