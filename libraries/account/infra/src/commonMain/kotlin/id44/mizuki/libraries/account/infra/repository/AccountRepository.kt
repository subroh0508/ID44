package id44.mizuki.libraries.account.infra.repository

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.shared.valueobject.HostName

interface AccountRepository {
    suspend fun fetchOwnAccount(
        hostName: HostName
    ) : Account

    fun fetchOwnAccounts(): List<Account>

    fun revokeAccount(hostName: HostName, account: Account)
}
