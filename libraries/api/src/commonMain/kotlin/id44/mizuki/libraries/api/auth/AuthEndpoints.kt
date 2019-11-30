package id44.mizuki.libraries.api.auth

import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential

internal object AuthEndpoints {
    fun postApps() = "/api/v1/apps"
    fun getOauthAuthorize() = "/oauth/authorize"
    fun postOauthToken() = "/oauth/token"
    fun getAccountsVerifyCredentials() = "/api/v1/accounts/verify_credentials"
}

internal const val POST_APPS = "/api/v1/apps"
internal const val GET_OAUTH_AUTHORIZE = "/oauth/authorize"
internal const val POST_OAUTH_TOKEN = "/oauth/token"
