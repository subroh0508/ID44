package id44.mizuki.libraries.api.auth.client

import id44.mizuki.libraries.api.auth.AuthEndpoints
import id44.mizuki.libraries.api.auth.Constants
import id44.mizuki.libraries.api.auth.params.PostApps
import id44.mizuki.libraries.api.auth.params.PostOauthToken
import id44.mizuki.libraries.api.auth.model.AccessToken
import id44.mizuki.libraries.api.auth.model.AppCredential
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.post
import io.ktor.http.URLProtocol

internal class MastodonAuthApiClient(
    private val httpClient: HttpClient,
    private val json: JsonSerializer = defaultSerializer()
): MastodonAuthApi {
    companion object {
        private const val RESPONSE_TYPE = "response_type"
        private const val CLIENT_ID = "client_id"
        private const val CLIENT_SECRET = "client_secret"
        private const val REDIRECT_URI = "redirect_uri"
        private const val SCOPE = "scope"
    }

    override suspend fun requestAppCredential(
        hostName: String,
        clientName: String,
        redirectUri: String
    ): AppCredential = httpClient.post(buildUrl(hostName, AuthEndpoints.postApps())) {
        body = json.write(PostApps.Request(clientName, redirectUri, Constants.SCOPE, Constants.WEBSITE))
    }

    override fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ): String = buildString {
        append("${buildUrl(hostName, AuthEndpoints.getOauthAuthorize())}?")
        append(
            mapOf(
                RESPONSE_TYPE to "code",
                CLIENT_ID to clientId,
                CLIENT_SECRET to clientSecret,
                REDIRECT_URI to redirectUri,
                SCOPE to Constants.ESCAPED_SCOPE
            ).map { (k, v) -> "$k=$v" }.joinToString("&")
        )
    }

    override suspend fun requestAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): AccessToken = httpClient.post(buildUrl(hostName, AuthEndpoints.postOauthToken())) {
        body = json.write(
            PostOauthToken.Request(clientId, clientSecret, redirectUri, code)
        )
    }

    private fun buildUrl(hostName: String, endpoint: String) = "${URLProtocol.HTTPS.name}://$hostName$endpoint"
}