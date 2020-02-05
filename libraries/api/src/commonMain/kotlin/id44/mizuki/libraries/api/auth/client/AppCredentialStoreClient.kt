package id44.mizuki.libraries.api.auth.client

import id44.mizuki.libraries.api.LocalPreferences

internal class AppCredentialStoreClient(
    private val preferences: LocalPreferences
) : AppCredentialStore {
    override fun getClientId(hostName: String): String? =
        preferences.getString(hostName, null)?.split(":")?.get(0)

    override fun getClientSecret(hostName: String): String? =
        preferences.getString(hostName, null)?.split(":")?.get(1)

    override fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String) =
            preferences.putString(hostName, "$clientId:$clientSecret")
}
