package id44.mizuki.libraries.account.infra.repository

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.libraries.api.params.VerifyAppCredential
import id44.mizuki.libraries.shared.valueobject.HostName

internal class AccountRepositoryImpl(
    private val api: MastodonApi,
    private val cache: LocalCacheStore
) : AccountRepository {
    override suspend fun fetchOwnAccount(hostName: HostName): Account =
        api.verifyAppCredential(hostName.value).also { ownAccount ->
            cache.cacheVerifyAppCredential(hostName.value, ownAccount)
        }.toEntity(hostName)

    override fun fetchOwnAccounts(): List<Account> =
        cache.getVerifyAppCredentials().map { (hostName, response) ->
            response.toEntity(HostName(hostName))
        }

    override fun revokeAccount(hostName: HostName, account: Account) =
        cache.removeVerifyAppCredential(hostName.value, account.id)

    private fun VerifyAppCredential.Response.toEntity(hostName: HostName) = Account(
        id = id,
        username = username,
        displayName = displayName,
        hostName = hostName
    )
}
