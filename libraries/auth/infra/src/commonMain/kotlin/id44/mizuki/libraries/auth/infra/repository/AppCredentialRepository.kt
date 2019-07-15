package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.model.AppCredential

interface AppCredentialRepository {
    suspend fun fetchAppCredential(hostName: String): AppCredential

    fun cacheAppCredential(hostName: String, credential: AppCredential)
}
