package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.MastodonAuthApiClient
import id44.mizuki.libraries.api.client.AppCredentialStore
import id44.mizuki.libraries.api.model.AppCredential
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppCredentialRepositoryImpl(
    private val apiClient: MastodonAuthApiClient,
    private val localStore: AppCredentialStore
) : AppCredentialRepository {
    override suspend fun fetchAppCredential(hostName: String): AppCredential
            = withContext(Dispatchers.Default) { apiClient.requestAppCredential(hostName) }

    override fun cacheAppCredential(hostName: String, credential: AppCredential) {
        localStore.cacheAppCredential(hostName, credential)
    }
}
