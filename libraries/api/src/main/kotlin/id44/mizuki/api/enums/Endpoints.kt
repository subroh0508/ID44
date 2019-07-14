package id44.mizuki.api.enums

internal enum class Endpoints(val url: String) {
    GET_OAUTH_AUTHORIZE("/oauth/authorize"),
    POST_OAUTH_TOKEN("/oauth/token")
}