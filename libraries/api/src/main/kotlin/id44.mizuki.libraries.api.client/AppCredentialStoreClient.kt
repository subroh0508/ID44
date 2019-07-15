package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit

actual class AppCredentialStoreClient(
    private val sharedPreferences: SharedPreferences
) : AppCredentialStore {
    override fun getClientId(hostName: String): String?
            = sharedPreferences.getString(hostName, null)?.split(":")?.get(0)

    override fun getClientSecret(hostName: String): String?
            = sharedPreferences.getString(hostName, null)?.split(":")?.get(1)

    override fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String) {
        sharedPreferences.edit {
            putString(hostName, "$clientId:$clientSecret")
        }
    }
}
