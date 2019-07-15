package id44.mizuki.api.client

import android.content.SharedPreferences
import androidx.core.content.edit
import id44.mizuki.api.model.AccessToken
import kotlinx.serialization.json.Json

actual class AccessTokenStoreClient(
    private val sharedPreferences: SharedPreferences
) : AccessTokenStore {
    override fun getAccessToken(hostName: String): AccessToken? {
        val json = sharedPreferences.getString(hostName, null) ?: return null

        return Json.parse(AccessToken.serializer(), json)
    }

    override fun cacheAccessToken(hostName: String, token: AccessToken) {
        sharedPreferences.edit {
            putString(hostName, Json.stringify(AccessToken.serializer(), token))
        }
    }

    override fun clearAccessToken(hostName: String) {
        sharedPreferences.edit { remove(hostName) }
    }
}
