package id44.mizuki.libraries.account.infra.repository

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.shared.valueobject.AccountId
import id44.mizuki.shared.valueobject.HostName

interface AccountRepository {
    suspend fun fetchOwnAccount() : Account

    fun fetchOwnAccounts(): List<Account>

    fun revokeAccount(hostName: HostName, id: AccountId)
}
