package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.shared.model.auth.ClientId
import id44.mizuki.shared.model.auth.ClientSecret
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri

internal class AppCredentialRepositoryImpl(
    private val apiClient: MastodonAuthApi,
    private val localStore: AppCredentialStore
) : AppCredentialRepository {
    override suspend fun fetchAppCredential(
        hostName: HostName,
        clientName: String,
        redirectUri: Uri
    ): Pair<ClientId, ClientSecret> {
        val (clientId, clientSecret) = apiClient.requestAppCredential(hostName.value, clientName, redirectUri.value)

        return ClientId(clientId) to ClientSecret(clientSecret)
    }

    override fun getClientId(hostName: HostName): ClientId? =
        localStore.getClientId(hostName.value)?.let(::ClientId)

    override fun getClientSecret(hostName: HostName): ClientSecret? =
        localStore.getClientSecret(hostName.value)?.let(::ClientSecret)

    override fun cacheAppCredential(hostName: HostName, clientId: ClientId, clientSecret: ClientSecret) {
        localStore.cacheAppCredential(hostName.value, clientId.value, clientSecret.value)
    }
}
