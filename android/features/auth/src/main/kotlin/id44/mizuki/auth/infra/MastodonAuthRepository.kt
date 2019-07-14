package id44.mizuki.auth.infra

import id44.mizuki.api.MastodonApi
import id44.mizuki.api.enums.Scope
import id44.mizuki.api.model.AccessToken
import io.ktor.client.HttpClient
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.post
import io.ktor.client.response.HttpResponse
import io.ktor.client.response.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json

class MastodonAuthRepository(
    private val httpClient: HttpClient
) : MastodonApi {
    lateinit var hostName: String

    override suspend fun authorize(clientId: String, redirectUri: String, vararg scope: Scope): String {
        return ""
    }

    override suspend fun requestAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): AccessToken = withContext(Dispatchers.Default) {
        val json = defaultSerializer()
        val response = httpClient.post<HttpResponse>("https://$hostName/oauth/token") {
            body = json.write(Request.AccessToken(clientId, clientSecret, redirectUri, code))
        }

        val token = Json.parse(
            Response.AccessToken.serializer(),
            response.readText(charset = Charsets.UTF_8)
        )

        AccessToken(token.accessToken, token.tokenType, token.scope, token.createdAt)
    }
}