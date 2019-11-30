package id44.mizuki.libraries.api

import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.header
import io.ktor.client.request.host
import io.ktor.http.HttpHeaders

internal fun String.appendParams(vararg params: Pair<String, Any>): String {
    if (params.isEmpty()) {
        return this
    }

    return buildString {
        append(this)
        append(params.joinToString("&") { (k, v) -> "$k=$v" })
    }
}

internal suspend inline fun <reified T: Any> HttpClient.get(urlString: String) =
    get<T>(urlString) {
        host = Credentials.nowHost.value
        header(HttpHeaders.Authorization, "Bearer ${Credentials.nowToken.value}")
    }
internal suspend inline fun <reified T: Any> HttpClient.post(urlString: String, obj: Any) =
    post<T>(urlString) {
        host = Credentials.nowHost.value
        header(HttpHeaders.Authorization, "Bearer ${Credentials.nowToken.value}")
        body = obj
    }

internal const val GET_ACCOUNTS_VERIFY_CREDENTIALS = "/api/v1/accounts/verify_credentials"
internal const val GET_ACCOUNTS = "/api/v1/accounts"
