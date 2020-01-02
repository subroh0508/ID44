package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.*
import id44.mizuki.libraries.api.GET_ACCOUNTS
import id44.mizuki.libraries.api.GET_ACCOUNTS_VERIFY_CREDENTIALS
import id44.mizuki.libraries.api.GET_TIMELINES_PUBLIC
import id44.mizuki.libraries.api.json.StatusJson
import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.api.params.GetTimelines
import id44.mizuki.libraries.shared.valueobject.HostName
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.post
import io.ktor.client.request.host
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.serialization.ImplicitReflectionSerializer

internal class MastodonApiClient(
    private val httpClient: HttpClient,
    private val provider: CredentialProvider
) : MastodonApi {
    override val host: HostName get() = provider.nowHost

    override suspend fun getVerifyAccountsCredential() = GetAccountsVerifyCredential.Response(
        raw = httpClient.get(GET_ACCOUNTS_VERIFY_CREDENTIALS)
    )
    override suspend fun getAccount(id: String): AccountJson =
        httpClient.get("$GET_ACCOUNTS/id")

    override suspend fun getTimelinesPublic(maxId: String?, limit: Int) = GetTimelines.Response(
        raw = httpClient.get(
            GET_TIMELINES_PUBLIC.append("max_id" to maxId, "limit" to limit)
        )
    ).raw.map(::StatusJson)
    override suspend fun getTimelinesLocal(maxId: String?, limit: Int) = GetTimelines.Response(
        raw = httpClient.get(
            GET_TIMELINES_PUBLIC.append("max_id" to maxId, "limit" to limit, "local" to true)
        )
    ).raw.map(::StatusJson)

    private suspend inline fun <reified T: Any> HttpClient.get(urlString: String) =
        get<T>(urlString, block = {
            host = provider.nowHost.value
            header(HttpHeaders.Authorization, "Bearer ${provider.nowToken.value}")
        })

    private suspend inline fun <reified T: Any> HttpClient.post(urlString: String) =
        post<T>(urlString, block = {
            host = provider.nowHost.value
            header(HttpHeaders.Authorization, "Bearer ${provider.nowToken.value}")
            contentType(ContentType.Application.Json)
        })
}
