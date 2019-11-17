package id44.mizuki.libraries.account.domain.usecase.fetchownaccounts

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.account.infra.repository.AccountRepository

internal class FetchOwnAccountsUseCaseImpl(
    private val repository: AccountRepository
) : FetchOwnAccountsUseCase {
    override fun execute(): List<Account> = repository.fetchOwnAccounts()
}
