package id44.mizuki.libraries.api.client

import android.content.SharedPreferences
import androidx.core.content.edit
import id44.mizuki.libraries.api.model.AppCredential
import kotlinx.serialization.json.Json

actual class AppCredentialStoreClient(
    private val sharedPreferences: SharedPreferences
) : AppCredentialStore {
    override fun getAppCredential(hostName: String): AppCredential? {
        val json = sharedPreferences.getString(hostName, null) ?: return null

        return Json.parse(AppCredential.serializer(), json)
    }

    override fun cacheAppCredential(hostName: String, credential: AppCredential) {
        sharedPreferences.edit {
            putString(hostName, Json.stringify(AppCredential.serializer(), credential))
        }
    }
}
