package id44.mizuki.libraries.account.infra

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.shared.valueobject.HostName

interface AccountRepository {
    suspend fun fetchMyAccount(
        hostName: HostName
    ) : Account

    fun fetchAccounts(): List<Account>
}
