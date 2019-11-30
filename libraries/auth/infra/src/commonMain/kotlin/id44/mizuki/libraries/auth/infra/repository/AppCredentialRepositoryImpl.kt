package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.auth.domain.valueobject.ClientId
import id44.mizuki.libraries.auth.domain.valueobject.ClientSecret
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri

internal class AppCredentialRepositoryImpl(
    private val apiClient: MastodonAuthApi,
    private val localStore: AppCredentialStore
) : AppCredentialRepository {
    override suspend fun fetchAppCredential(
        hostName: HostName,
        clientName: String,
        redirectUri: Uri
    ): Pair<ClientId, ClientSecret> {
        val (clientId, clientSecret) = apiClient.requestAppCredential(hostName.value, clientName, redirectUri.toString())

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
