package id44.mizuki.infra.auth.repository

import id44.mizuki.shared.model.auth.ClientId
import id44.mizuki.shared.model.auth.ClientSecret
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri

interface AppCredentialRepository {
    suspend fun fetchAppCredential(
        hostName: HostName,
        clientName: String,
        redirectUri: Uri
    ): Pair<ClientId, ClientSecret>

    fun getClientId(hostName: HostName): ClientId?

    fun getClientSecret(hostName: HostName): ClientSecret?

    fun cacheAppCredential(hostName: HostName, clientId: ClientId, clientSecret: ClientSecret)
}
