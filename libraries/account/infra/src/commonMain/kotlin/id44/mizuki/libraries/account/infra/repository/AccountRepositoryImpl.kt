package id44.mizuki.libraries.account.infra.repository

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

internal class AccountRepositoryImpl(
    private val api: MastodonApi,
    private val cache: LocalCacheStore
) : AccountRepository {
    override suspend fun fetchOwnAccount(hostName: HostName): Account =
        api.getVerifyAccountsCredential(hostName.value).also { ownAccount ->
            cache.cacheVerifyAccountsCredential(hostName.value, ownAccount)
        }.toEntity(hostName)

    override fun fetchOwnAccounts(): List<Account> =
        cache.getVerifyAccountsCredentials().map { (hostName, response) ->
            response.toEntity(HostName(hostName))
        }

    override fun revokeAccount(hostName: HostName, account: Account) =
        cache.removeVerifyAccountsCredential(hostName.value, account.id.value)

    private fun GetAccountsVerifyCredential.Response.toEntity(hostName: HostName) = Account(
        id = AccountId(id),
        username = username,
        displayName = displayName,
        hostName = hostName
    )
}
