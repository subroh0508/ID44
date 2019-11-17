package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.params.GetAppsVerifyCredential

interface LocalCacheStore {
    fun getVerifyAppCredentials(): List<GetAppsVerifyCredential.Cache>
    fun cacheVerifyAppCredential(hostName: String, response: GetAppsVerifyCredential.Response)
    fun removeVerifyAppCredential(hostName: String, id: String)
}
