package id44.mizuki.libraries.auth.infra.repository

interface AppCredentialRepository {
    suspend fun fetchAppCredential(hostName: String): Pair<String, String>

    fun getClientId(hostName: String): String?

    fun getClientSecret(hostName: String): String?

    fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String)
}
