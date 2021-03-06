package id44.mizuki.api.client

import id44.mizuki.api.LocalPreferences
import id44.mizuki.api.params.GetAccountsVerifyCredential
import kotlinx.serialization.builtins.ListSerializer

internal class LocalCacheStoreClient(
    private val preferences: LocalPreferences
) : LocalCacheStore {
    override fun getVerifyAccountsCredentials(): List<GetAccountsVerifyCredential.Cache> =
        preferences.get(VERIFY_ACCOUNTS_CREDENTIALS, ListSerializer(GetAccountsVerifyCredential.Cache.serializer())) ?: listOf()
    override fun getVerifyAccountsCredential(hostName: String, id: String): GetAccountsVerifyCredential.Cache? =
        getVerifyAccountsCredentials().find { it.hostName == hostName && it.response.id == id }
    override fun cacheVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response) {
        if (getVerifyAccountsCredential(hostName, response.id) != null) {
            return
        }

        val caches = getVerifyAccountsCredentials().toMutableList()

        preferences.put(
            VERIFY_ACCOUNTS_CREDENTIALS,
            ListSerializer(GetAccountsVerifyCredential.Cache.serializer()),
            caches.apply { add(GetAccountsVerifyCredential.Cache(hostName, response)) }
        )
    }
    override fun removeVerifyAccountsCredential(hostName: String, id: String) {
        preferences.put(
            VERIFY_ACCOUNTS_CREDENTIALS,
            ListSerializer(GetAccountsVerifyCredential.Cache.serializer()),
            getVerifyAccountsCredentials().filter { it.hostName != hostName || it.response.id != id }
        )
    }
    override fun getNowVerifyAccountsCredential(): GetAccountsVerifyCredential.Cache? =
        preferences.get(NOW_VERIFY_ACCOUNTS_CREDENTIAL, GetAccountsVerifyCredential.Cache.serializer())

    override fun cacheNowVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response) {
        preferences.put(
            NOW_VERIFY_ACCOUNTS_CREDENTIAL,
            GetAccountsVerifyCredential.Cache.serializer(),
            GetAccountsVerifyCredential.Cache(hostName, response)
        )
    }

    companion object {
        private const val VERIFY_ACCOUNTS_CREDENTIALS = "VERIFY_ACCOUNTS_CREDENTIALS"
        private const val NOW_VERIFY_ACCOUNTS_CREDENTIAL = "NOW_VERIFY_ACCOUNTS_CREDENTIAL"
    }
}
