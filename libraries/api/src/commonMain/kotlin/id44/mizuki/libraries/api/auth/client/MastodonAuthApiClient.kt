package id44.mizuki.libraries.api.auth.client

import id44.mizuki.libraries.api.auth.AuthEndpoints
import id44.mizuki.libraries.api.auth.Constants
import id44.mizuki.libraries.api.auth.params.PostApps
import id44.mizuki.libraries.api.auth.params.PostOauthToken
import id44.mizuki.libraries.api.model.AccessToken
import id44.mizuki.libraries.api.model.AppCredential
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonSerializer
import io.ktor.client.features.json.defaultSerializer
import io.ktor.client.request.post

class MastodonAuthApiClient(
    private val httpClient: HttpClient,
    private val json: JsonSerializer = defaultSerializer()
): MastodonAuthApi {
    override suspend fun requestAppCredential(
        hostName: String
    ): AppCredential = httpClient.post(buildUrl(hostName, AuthEndpoints.POST_APPS)) {
        body = json.write(
            PostApps.Request(Constants.CLIENT_NAME, Constants.REDIRECT_URI, Constants.SCOPE, Constants.WEBSITE)
        )
    }

    override fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String
    ): String = buildString {
        append("${buildUrl(hostName, AuthEndpoints.GET_OAUTH_AUTHORIZE)}?")
        append(
            mapOf(
                "response_type" to "code",
                "client_id" to clientId,
                "client_secret" to clientSecret,
                "redirect_uri" to Constants.REDIRECT_URI,
                "scope" to Constants.ESCAPED_SCOPE
            ).map { (k, v) -> "$k=$v" }.joinToString("&")
        )
    }

    override suspend fun requestAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        code: String
    ): AccessToken = httpClient.post(buildUrl(hostName, AuthEndpoints.POST_OAUTH_TOKEN)) {
        body = json.write(
            PostOauthToken.Request(clientId, clientSecret, Constants.REDIRECT_URI, code)
        )
    }

    private fun buildUrl(hostName: String, endpoint: AuthEndpoints) = "https://$hostName${endpoint.url}"
}