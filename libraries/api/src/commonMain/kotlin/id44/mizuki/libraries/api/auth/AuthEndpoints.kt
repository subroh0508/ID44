package id44.mizuki.libraries.api.auth

internal enum class AuthEndpoints(val url: String) {
    GET_OAUTH_AUTHORIZE("/oauth/authorize"),
    POST_OAUTH_TOKEN("/oauth/token")
}