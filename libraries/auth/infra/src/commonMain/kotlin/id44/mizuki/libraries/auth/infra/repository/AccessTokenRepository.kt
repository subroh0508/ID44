package id44.mizuki.libraries.auth.infra.repository

import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

interface AccessTokenRepository {
    fun existAnyAuthenticatedAccounts(): Boolean

    fun existAccessToken(hostName: HostName, id: AccountId): Boolean

    fun getAccessToken(hostName: HostName, id: AccountId): AccessToken

    fun clearAccessToken(hostName: HostName, id: AccountId)

    fun saveNowAuthenticatedAccount(hostName: HostName, id: AccountId)
}
