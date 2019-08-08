package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit
import id44.mizuki.libraries.api.TokenExpiredException

internal actual class AccessTokenStoreClient(
    private val sharedPreferences: SharedPreferences
) : AccessTokenStore {
    override fun getAccessToken(hostName: String): String
            = sharedPreferences.getString(hostName, null) ?: throw TokenExpiredException()

    override fun cacheAccessToken(hostName: String, token: String) {
        sharedPreferences.edit { putString(hostName, token) }
    }

    override fun clearAccessToken(hostName: String) {
        sharedPreferences.edit { remove(hostName) }
    }
}
