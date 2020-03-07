package id44.mizuki.api.client

import id44.mizuki.api.LocalPreferences

class AccessTokenStoreClient(
    private val preferences: LocalPreferences
) : AccessTokenStore {
    companion object {
        private const val ALL_AUTHENTICATED_ACCOUNT_ID = "ALL_AUTHENTICATED_ACCOUNT_ID"
    }

    override fun getAllAuthenticatedAccountIds(): List<String> =
        preferences.getStringSet(ALL_AUTHENTICATED_ACCOUNT_ID, setOf())?.toList() ?: listOf()

    override fun getAccessToken(id: String): String? =
        preferences.getString(id, null)

    override fun cacheAccessToken(id: String, token: String) {
        val keys = getAllAuthenticatedAccountIds().toMutableSet()

        preferences.putString(id, token)
        preferences.putStringSet(ALL_AUTHENTICATED_ACCOUNT_ID, keys.apply { if (!contains(id)) add(id) })
    }

    override fun clearAccessToken(id: String) {
        val accountIds = getAllAuthenticatedAccountIds()

        preferences.remove(id)
        preferences.putStringSet(ALL_AUTHENTICATED_ACCOUNT_ID, accountIds.filterNot { it == id }.toSet())
    }
}
