package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AppCredentialStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AppCredentialRepositoryImpl(
    private val apiClient: MastodonAuthApi,
    private val localStore: AppCredentialStore
) : AppCredentialRepository {
    override suspend fun fetchAppCredential(hostName: String): Pair<String, String> = withContext(Dispatchers.Default) {
        val (clientId, clientSecret) = apiClient.requestAppCredential(hostName)

        clientId to clientSecret
    }

    override fun getClientId(hostName: String): String? = localStore.getClientId(hostName)

    override fun getClientSecret(hostName: String): String? = localStore.getClientSecret(hostName)

    override fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String) {
        localStore.cacheAppCredential(hostName, clientId, clientSecret)
    }
}
