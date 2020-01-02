package id44.mizuki.libraries.api

internal fun String.append(vararg params: Pair<String, Any?>): String {
    if (params.isEmpty()) {
        return this
    }

    return buildString {
        append("$this?")
        append(params.filter { (_, v) -> v != null }.joinToString("&") { (k, v) -> "$k=$v" })
    }
}

internal const val GET_ACCOUNTS_VERIFY_CREDENTIALS = "/api/v1/accounts/verify_credentials"
internal const val GET_ACCOUNTS = "/api/v1/accounts"

internal const val GET_TIMELINES_PUBLIC = "/api/v1/timelines/public"
