package id44.mizuki.auth.infra

import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.shared.valueobject.HostName

internal class AccessTokenRepository(
    private val accessTokenStore: AccessTokenStore
) {
    fun getAuthenticatedHostNames(): List<HostName> =
        accessTokenStore.getAuthenticatedHostNames().map(::HostName)

    fun clearAccessToken(hostName: String) = accessTokenStore.clearAccessToken(hostName)
}
