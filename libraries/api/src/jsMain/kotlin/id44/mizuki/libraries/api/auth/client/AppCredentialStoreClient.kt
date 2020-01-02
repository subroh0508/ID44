package id44.mizuki.libraries.api.auth.client

internal actual class AppCredentialStoreClient : AppCredentialStore {
    override fun cacheAppCredential(hostName: String, clientId: String, clientSecret: String) = Unit
    override fun getClientId(hostName: String): String? = null
    override fun getClientSecret(hostName: String): String? = null
}
