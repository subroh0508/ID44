package id44.mizuki.libraries.account.infra.repository

import id44.mizuki.shared.model.account.Account
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

interface AccountRepository {
    suspend fun fetchOwnAccount() : Account

    fun fetchOwnAccounts(): List<Account>

    fun revokeAccount(hostName: HostName, id: AccountId)
}
