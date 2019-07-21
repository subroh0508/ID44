package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.Endpoints
import id44.mizuki.libraries.api.auth.params.GetAccount
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.isSuccess

internal class MastodonApiClient(
    private val httpClient: HttpClient,
    private val json: JsonSerializer
) : MastodonApi {
    override suspend fun verifyAppCredential(): Boolean {
        val response = httpClient.get<HttpResponse>(
            Endpoints.getAppsVerifyCredentials()
        )

        return response.status.isSuccess()
    }

    override suspend fun getAccount(id: String): GetAccount.Response
            = httpClient.get(Endpoints.getAccounts(id))
}
