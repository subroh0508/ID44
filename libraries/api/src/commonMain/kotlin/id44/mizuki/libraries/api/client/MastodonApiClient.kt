package id44.mizuki.libraries.api.client

import id44.mizuki.libraries.api.Credentials
import id44.mizuki.libraries.api.GET_ACCOUNTS
import id44.mizuki.libraries.api.GET_ACCOUNTS_VERIFY_CREDENTIALS
import id44.mizuki.libraries.api.get
import id44.mizuki.libraries.api.json.account.AccountJson
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.shared.valueobject.HostName
import io.ktor.client.HttpClient

internal class MastodonApiClient(
    private val httpClient: HttpClient
) : MastodonApi {
    override val host: HostName get() = Credentials.nowHost

    override suspend fun getVerifyAccountsCredential(): GetAccountsVerifyCredential.Response =
        httpClient.get(GET_ACCOUNTS_VERIFY_CREDENTIALS)

    override suspend fun getAccount(id: String): AccountJson =
        httpClient.get("$GET_ACCOUNTS/id")
}
