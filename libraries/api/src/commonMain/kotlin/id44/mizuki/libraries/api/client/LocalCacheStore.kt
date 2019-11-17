package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.params.VerifyAppCredential

interface LocalCacheStore {
    fun getVerifyAppCredentials(): List<VerifyAppCredential.Cache>
    fun cacheVerifyAppCredential(hostName: String, response: VerifyAppCredential.Response)
    fun removeVerifyAppCredential(hostName: String, id: String)
}
