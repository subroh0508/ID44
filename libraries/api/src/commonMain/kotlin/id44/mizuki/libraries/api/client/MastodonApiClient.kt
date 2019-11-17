package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.Endpoints
import id44.mizuki.libraries.api.json.AccountJson
import id44.mizuki.libraries.api.params.VerifyAppCredential
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.isSuccess

internal class MastodonApiClient(
    private val httpClient: HttpClient,
    private val json: JsonSerializer
) : MastodonApi {
    override suspend fun verifyAppCredential(): VerifyAppCredential.Response =
        httpClient.get(Endpoints.getAppsVerifyCredentials())

    override suspend fun getAccount(id: String): AccountJson =
        httpClient.get(Endpoints.getAccounts(id))
}
