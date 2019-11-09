package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit
import id44.mizuki.libraries.api.TokenExpiredException

actual class AccessTokenStoreClient(
    private val sharedPreferences: SharedPreferences
) : AccessTokenStore {
    companion object {
        private const val AUTHENTICATED_HOST_NAMES = "AUTHENTICATED_HOST_NAMES"
    }

    override fun getAuthenticatedHostNames(): List<String>
            = sharedPreferences.getStringSet(AUTHENTICATED_HOST_NAMES, setOf())?.toList() ?: listOf()

    override fun getAccessToken(hostName: String): String
            = sharedPreferences.getString(hostName, null) ?: throw TokenExpiredException(hostName)

    override fun cacheAccessToken(hostName: String, token: String) {
        val hostNames = getAuthenticatedHostNames().toMutableSet()

        sharedPreferences.edit {
            putString(hostName, token)
            putStringSet(AUTHENTICATED_HOST_NAMES, hostNames.apply { add(hostName) })
        }
    }

    override fun clearAccessToken(hostName: String) {
        val hostNames = getAuthenticatedHostNames().toMutableSet()

        sharedPreferences.edit {
            remove(hostName)
            putStringSet(AUTHENTICATED_HOST_NAMES, hostNames.apply { remove(hostName) })
        }
    }
}
