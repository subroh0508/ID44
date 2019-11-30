package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit

actual class AccessTokenStoreClient(
    private val sharedPreferences: SharedPreferences
) : AccessTokenStore {
    companion object {
        private const val ALL_AUTHENTICATED_ACCOUNT_ID = "ALL_AUTHENTICATED_ACCOUNT_ID"
    }

    override fun getAllAuthenticatedAccountIds(): List<String> =
        sharedPreferences.getStringSet(ALL_AUTHENTICATED_ACCOUNT_ID, setOf())?.toList() ?: listOf()

    override fun getAccessToken(id: String): String? =
        sharedPreferences.getString(id, null)

    override fun cacheAccessToken(id: String, token: String) {
        val keys = getAllAuthenticatedAccountIds().toMutableSet()

        sharedPreferences.edit {
            putString(id, token)
            putStringSet(ALL_AUTHENTICATED_ACCOUNT_ID, keys.apply { if (!contains(id)) add(id) })
        }
    }

    override fun clearAccessToken(id: String) {
        val accountIds = getAllAuthenticatedAccountIds()

        sharedPreferences.edit {
            remove(id)
            putStringSet(ALL_AUTHENTICATED_ACCOUNT_ID, accountIds.apply { remove(id) }.toSet())
        }
    }
}
