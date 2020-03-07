package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.api.CredentialProvider
import id44.mizuki.api.TokenExpiredException
import id44.mizuki.api.client.AccessTokenStore
import id44.mizuki.api.client.LocalCacheStore
import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

internal class AccessTokenRepositoryImpl(
    private val authLocalStore: AccessTokenStore,
    private val localStore: LocalCacheStore
) : AccessTokenRepository, CredentialProvider {
    override fun existAnyAuthenticatedAccounts() =
        authLocalStore.getAllAuthenticatedAccountIds().isNotEmpty()

    override fun existAccessToken(hostName: HostName, id: AccountId) =
        getVerifyAccountsCredentialId(hostName, id)?.let(authLocalStore::getAccessToken) != null

    override fun getAccessToken(hostName: HostName, id: AccountId) = AccessToken(
        getVerifyAccountsCredentialId(hostName, id)?.let(
            authLocalStore::getAccessToken
        ) ?: throw TokenExpiredException(hostName, id)
    )

    override fun clearAccessToken(hostName: HostName, id: AccountId) =
        authLocalStore.clearAccessToken(id.value)

    override fun saveNowAuthenticatedAccount(hostName: HostName, id: AccountId) {
        localStore.getVerifyAccountsCredential(hostName.value, id.value)?.let { (host, response) ->
            localStore.cacheNowVerifyAccountsCredential(host, response)
        }
    }

    private fun getVerifyAccountsCredentialId(hostName: HostName, id: AccountId) =
        localStore.getVerifyAccountsCredential(hostName.value, id.value)?.response?.id

    override val nowToken get() = localStore.getNowVerifyAccountsCredential()?.let { (host, response) ->
        getAccessToken(HostName(host), AccountId(response.id))
    } ?: throw IllegalStateException()
    override val nowHost get() =
        HostName(localStore.getNowVerifyAccountsCredential()?.hostName ?: throw IllegalStateException())
}
