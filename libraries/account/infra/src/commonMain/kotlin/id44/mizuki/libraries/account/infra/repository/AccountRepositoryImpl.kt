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
    override suspend fun fetchOwnAccount(): Account =
        api.getVerifyAccountsCredential().also { ownAccount ->
            cache.cacheVerifyAccountsCredential(api.host.value, ownAccount)
        }.toEntity(api.host)

    override fun fetchOwnAccounts(): List<Account> =
        cache.getVerifyAccountsCredentials().map { (hostName, response) ->
            response.toEntity(HostName(hostName))
        }

    override fun revokeAccount(hostName: HostName, id: AccountId) =
        cache.removeVerifyAccountsCredential(hostName.value, id.value)

    private fun GetAccountsVerifyCredential.Response.toEntity(hostName: HostName) = Account(
        id = id,
        username = username,
        displayName = displayName,
        hostName = hostName.value,
        avatar = avatar
    )
}
