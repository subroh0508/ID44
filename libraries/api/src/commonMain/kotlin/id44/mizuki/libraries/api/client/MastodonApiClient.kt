package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.Endpoints
import id44.mizuki.libraries.api.json.AccountJson
import id44.mizuki.libraries.api.params.VerifyAppCredential
import io.ktor.client.HttpClient
import io.ktor.client.request.get

internal class MastodonApiClient(
    private val httpClient: HttpClient
) : MastodonApi {
    override suspend fun verifyAppCredential(hostName: String): VerifyAppCredential.Response =
        httpClient.get(
            host = hostName,
            path = Endpoints.getAppsVerifyCredentials()
        )

    override suspend fun getAccount(hostName: String, id: String): AccountJson =
        httpClient.get(
            host = hostName,
            path = Endpoints.getAccounts(id)
        )
}
