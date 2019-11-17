package id44.mizuki.libraries.api

internal object Endpoints {
    fun getAccountsVerifyCredentials() = "/api/v1/accounts/verify_credentials"

    fun getAccounts(id: String) = "/api/v1/accounts/$id"

    private fun String.appendParams(vararg params: Pair<String, Any>): String {
        if (params.isEmpty()) {
            return this
        }

        return buildString {
            append(this)
            append(params.joinToString("&") { (k, v) -> "$k=$v" })
        }
    }
}
