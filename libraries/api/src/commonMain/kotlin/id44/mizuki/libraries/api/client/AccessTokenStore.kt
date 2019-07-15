package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.model.AccessToken

interface AccessTokenStore {
    fun getAccessToken(hostName: String): AccessToken?

    fun cacheAccessToken(hostName: String, token: AccessToken)

    fun clearAccessToken(hostName: String)
}
