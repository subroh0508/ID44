package id44.mizuki.api.client

import id44.mizuki.api.params.GetAccountsVerifyCredential

interface LocalCacheStore {
    fun getVerifyAccountsCredentials(): List<GetAccountsVerifyCredential.Cache>
    fun getVerifyAccountsCredential(hostName: String, id: String): GetAccountsVerifyCredential.Cache?
    fun cacheVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response)
    fun removeVerifyAccountsCredential(hostName: String, id: String)
    fun getNowVerifyAccountsCredential(): GetAccountsVerifyCredential.Cache?
    fun cacheNowVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response)
}
