package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.Endpoints
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.response.HttpResponse
import io.ktor.http.isSuccess

class MastodonApiClient(
    private val httpClient: HttpClient,
    private val hostName: String
) : MastodonApi {
    override suspend fun verifyAppCredential(): Boolean {
        val response = httpClient.get<HttpResponse>(buildUrl(Endpoints.GET_APPS_VERIFY_CREDENTIALS))

        return response.status.isSuccess()
    }

    private fun buildUrl(
        endpoint: Endpoints,
        vararg params: Pair<String, Any>
    ): String {
        if (params.isEmpty()) {
            return "https://$hostName/${endpoint.url}"
        }

        return buildString {
            append("https://$hostName/${endpoint.url}?")
            append(params.joinToString("&") { (k, v) -> "$k=$v" })
        }
    }
}
