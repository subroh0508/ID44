package id44.mizuki.api

import id44.mizuki.api.enums.Scope
import id44.mizuki.api.model.AccessToken

interface MastodonApi {
    suspend fun authorize(
        clientId: String,
        redirectUri: String,
        vararg scope: Scope
    ): String

    suspend fun requestAccessToken(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        code: String
    ): AccessToken
}