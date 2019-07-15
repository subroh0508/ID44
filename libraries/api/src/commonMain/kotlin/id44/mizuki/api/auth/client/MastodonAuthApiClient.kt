package id44.mizuki.api.auth.client

import id44.mizuki.api.auth.AuthEndpoints
import id44.mizuki.api.model.AccessToken
import id44.mizuki.api.params.PostOauthToken
import io.ktor.client.HttpClient
import io.ktor.client.request.post

class MastodonAuthApiClient(
    private val httpClient: HttpClient
): MastodonAuthApi {
    override fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        scope: String
    ): String = buildString {
        append("${buildUrl(hostName, AuthEndpoints.GET_OAUTH_AUTHORIZE)}?")
        append(
            mapOf(
                "response_type" to "code",
                "client_id" to clientId,
                "client_secret" to clientSecret,
                "redirect_uri" to redirectUri,
                "scope" to scope
            ).map { (k, v) -> "$k=$v" }.joinToString("&")
        )
    }

    override suspend fun requestAccessToken(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): AccessToken = httpClient.post(buildUrl(hostName, AuthEndpoints.POST_OAUTH_TOKEN)) {
        body = PostOauthToken.Request(clientId, clientSecret, redirectUri, code)
    }

    private fun buildUrl(hostName: String, endpoint: AuthEndpoints) = "https://$hostName/${endpoint.url}"
}