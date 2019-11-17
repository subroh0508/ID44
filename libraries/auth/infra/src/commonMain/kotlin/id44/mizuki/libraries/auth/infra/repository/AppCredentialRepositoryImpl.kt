package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi

internal class AppCredentialRepositoryImpl(
    private val apiClient: MastodonAuthApi,
    private val localStore: AppCredentialStore
) : AppCredentialRepository {
    override suspend fun fetchAppCredential(
        hostName: String,
        clientName: String,
        redirectUri: String
    ): Pair<String, String> {
        val (clientId, clientSecret) = apiClient.requestAppCredential(hostName, clientName, redirectUri)

        return clientId to clientSecret
    }

    override fun getClientId(hostName: String): String? = localStore.getClientId(hostName)

    override fun getClientSecret(hostName: String): String? = localStore.getClientSecret(hostName)

    override fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String) {
        localStore.cacheAppCredential(hostName, clientId, clientSecret)
    }
}
