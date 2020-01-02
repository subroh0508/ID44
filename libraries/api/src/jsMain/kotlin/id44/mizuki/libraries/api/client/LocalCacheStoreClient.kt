package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential

internal actual class LocalCacheStoreClient : LocalCacheStore {
    override fun cacheNowVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response) = Unit
    override fun cacheVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response) = Unit
    override fun getNowVerifyAccountsCredential(): GetAccountsVerifyCredential.Cache? = null
    override fun getVerifyAccountsCredential(hostName: String, id: String): GetAccountsVerifyCredential.Cache? = null
    override fun getVerifyAccountsCredentials(): List<GetAccountsVerifyCredential.Cache> = listOf()
    override fun removeVerifyAccountsCredential(hostName: String, id: String) = Unit
}
