package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

internal actual class LocalCacheStoreClient(
    private val sharedPreferences: SharedPreferences,
    private val json: Json
) : LocalCacheStore {
    override fun getVerifyAccountsCredentials(): List<GetAccountsVerifyCredential.Cache> =
        get(VERIFY_ACCOUNTS_CREDENTIALS, GetAccountsVerifyCredential.Cache.serializer().list) ?: mutableListOf()
    override fun getVerifyAccountsCredential(hostName: String, id: String): GetAccountsVerifyCredential.Cache? =
        getVerifyAccountsCredentials().find { it.hostName == hostName && it.response.id == id }
    override fun cacheVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response) {
        if (getVerifyAccountsCredential(hostName, response.id) != null) {
            return
        }

        val caches = getVerifyAccountsCredentials().toMutableList()

        put(
            VERIFY_ACCOUNTS_CREDENTIALS,
            GetAccountsVerifyCredential.Cache.serializer().list,
            caches.apply { add(GetAccountsVerifyCredential.Cache(hostName, response)) }
        )
    }
    override fun removeVerifyAccountsCredential(hostName: String, id: String) {
        put(
            VERIFY_ACCOUNTS_CREDENTIALS,
            GetAccountsVerifyCredential.Cache.serializer().list,
            getVerifyAccountsCredentials().filter { it.hostName != hostName || it.response.id != id }
        )
    }
    override fun getNowVerifyAccountsCredential(): GetAccountsVerifyCredential.Cache? =
        get(NOW_VERIFY_ACCOUNTS_CREDENTIAL, GetAccountsVerifyCredential.Cache.serializer())

    override fun cacheNowVerifyAccountsCredential(hostName: String, response: GetAccountsVerifyCredential.Response) {
        put(
            NOW_VERIFY_ACCOUNTS_CREDENTIAL,
            GetAccountsVerifyCredential.Cache.serializer(),
            GetAccountsVerifyCredential.Cache(hostName, response)
        )
    }


    private fun <T: Any> get(key: String, strategy: DeserializationStrategy<T>): T? =
        sharedPreferences.getString(key, null)?.let { json.parse(strategy, it) }
    private fun <T: Any> put(key: String, strategy: SerializationStrategy<T>, value: T) =
        sharedPreferences.edit { putString(key, json.stringify(strategy, value)) }

    companion object {
        private const val VERIFY_ACCOUNTS_CREDENTIALS = "VERIFY_ACCOUNTS_CREDENTIALS"
        private const val NOW_VERIFY_ACCOUNTS_CREDENTIAL = "NOW_VERIFY_ACCOUNTS_CREDENTIAL"
    }
}
