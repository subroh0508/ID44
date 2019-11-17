package id44.mizuki.libraries.api.auth

internal object AuthEndpoints {
    fun postApps() = "/api/v1/apps"
    fun getOauthAuthorize() = "/oauth/authorize"
    fun postOauthToken() = "/oauth/token"
    fun getAppsVerifyCredentials() = "/api/v1/accounts/verify_credentials"
}
