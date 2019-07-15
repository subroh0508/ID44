package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.model.AppCredential

interface AppCredentialStore {
    fun getAppCredential(hostName: String): AppCredential?

    fun cacheAppCredential(hostName: String, credential: AppCredential)
}
