package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit
import id44.mizuki.libraries.api.params.GetAppsVerifyCredential
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json
import kotlinx.serialization.list

internal actual class LocalCacheStoreClient(
    private val sharedPreferences: SharedPreferences,
    private val json: Json
) : LocalCacheStore {
    override fun getVerifyAppCredentials(): List<GetAppsVerifyCredential.Cache> =
        get(VERIFY_APP_CREDENTIALS, GetAppsVerifyCredential.Cache.serializer().list)
    override fun cacheVerifyAppCredential(hostName: String, response: GetAppsVerifyCredential.Response) {
        val caches = getVerifyAppCredentials().toMutableList()
        if (caches.find { it.hostName == hostName && it.response.id == response.id } != null) {
            return
        }

        put(
            VERIFY_APP_CREDENTIALS,
            GetAppsVerifyCredential.Cache.serializer().list,
            caches.apply { add(GetAppsVerifyCredential.Cache(hostName, response)) }
        )
    }
    override fun removeVerifyAppCredential(hostName: String, id: String) {
        put(
            VERIFY_APP_CREDENTIALS,
            GetAppsVerifyCredential.Cache.serializer().list,
            getVerifyAppCredentials().filter { it.hostName != hostName || it.response.id != id }
        )
    }

    private fun <T: Any> get(key: String, strategy: DeserializationStrategy<T>): T =
        json.parse(strategy, sharedPreferences.getString(key, "") ?: "")
    private fun <T: Any> put(key: String, strategy: SerializationStrategy<T>, value: T) =
        sharedPreferences.edit { putString(key, json.stringify(strategy, value)) }

    companion object {
        private const val VERIFY_APP_CREDENTIALS = "VERIFY_APP_CREDENTIALS"
    }
}
