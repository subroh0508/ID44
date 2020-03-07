package id44.mizuki.libraries.account.domain.usecase.fetchownaccounts

import id44.mizuki.shared.model.account.Account

interface FetchOwnAccountsUseCase {
    fun execute(): List<Account>
}
