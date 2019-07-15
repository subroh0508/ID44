package id44.mizuki.api.client

import io.ktor.client.HttpClient

class MastodonApiClient(
    private val httpClient: HttpClient,
    private val hostName: String
) : MastodonApi {
}
