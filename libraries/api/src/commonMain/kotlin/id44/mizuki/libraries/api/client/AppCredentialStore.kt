package id44.mizuki.libraries.api.client

interface AppCredentialStore {
    fun getClientId(hostName: String): String?

    fun getClientSecret(hostName: String): String?

    fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String)
}
