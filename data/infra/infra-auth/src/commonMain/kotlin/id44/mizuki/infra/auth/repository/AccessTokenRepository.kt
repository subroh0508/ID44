package id44.mizuki.infra.auth.repository

import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

interface AccessTokenRepository {
    fun existAnyAuthenticatedAccounts(): Boolean

    fun existAccessToken(hostName: HostName, id: AccountId): Boolean

    fun getAccessToken(hostName: HostName, id: AccountId): AccessToken

    fun clearAccessToken(hostName: HostName, id: AccountId)

    fun saveNowAuthenticatedAccount(hostName: HostName, id: AccountId)
}
