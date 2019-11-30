package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.api.TokenExpiredException
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

internal class AccessTokenRepositoryImpl(
    private val authLocalStore: AccessTokenStore,
    private val localStore: LocalCacheStore
) : AccessTokenRepository {
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

    private fun getVerifyAccountsCredentialId(hostName: HostName, id: AccountId) =
        localStore.getVerifyAccountsCredential(hostName.value, id.value)?.response?.id
}
