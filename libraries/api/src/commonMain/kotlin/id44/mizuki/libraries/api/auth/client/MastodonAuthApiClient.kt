package id44.mizuki.libraries.api.auth.client

import id44.mizuki.libraries.api.GET_ACCOUNTS_VERIFY_CREDENTIALS
import id44.mizuki.libraries.api.auth.Constants
import id44.mizuki.libraries.api.auth.GET_OAUTH_AUTHORIZE
import id44.mizuki.libraries.api.auth.POST_APPS
import id44.mizuki.libraries.api.auth.POST_OAUTH_TOKEN
import id44.mizuki.libraries.api.auth.model.AccessToken
import id44.mizuki.libraries.api.auth.model.AppCredential
import id44.mizuki.libraries.api.auth.params.PostApps
import id44.mizuki.libraries.api.auth.params.PostOauthToken
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.client.request.post
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLProtocol
import io.ktor.http.contentType

internal class MastodonAuthApiClient(
    private val httpClient: HttpClient
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
    ): AppCredential = httpClient.post(POST_APPS) {
        host = hostName
        contentType(ContentType.Application.Json)
        body = PostApps.Request(clientName, redirectUri, Constants.SCOPE, Constants.WEBSITE)
    }

    override fun buildAuthorizeUrl(
        hostName: String,
        clientId: String,
        clientSecret: String,
        redirectUri: String
    ): String = buildString {
        append("${URLProtocol.HTTPS.name}://$hostName$GET_OAUTH_AUTHORIZE?")
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
    ): AccessToken = httpClient.post(POST_OAUTH_TOKEN) {
        host = hostName
        contentType(ContentType.Application.Json)
        body = PostOauthToken.Request(clientId, clientSecret, redirectUri, code)
    }

    override suspend fun getVerifyAccountsCredentials(
        hostName: String,
        accessToken: String
    ): GetAccountsVerifyCredential.Response = httpClient.get(GET_ACCOUNTS_VERIFY_CREDENTIALS) {
        host = hostName
        header(HttpHeaders.Authorization, "Bearer $accessToken")
    }
}
