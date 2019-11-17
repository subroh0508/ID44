package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential

interface LocalCacheStore {
    fun getVerifyAccountsCredentials(): List<GetAccountsVerifyCredential.Cache>
    fun cacheVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response)
    fun removeVerifyAccountsCredential(hostName: String, id: String)
}
