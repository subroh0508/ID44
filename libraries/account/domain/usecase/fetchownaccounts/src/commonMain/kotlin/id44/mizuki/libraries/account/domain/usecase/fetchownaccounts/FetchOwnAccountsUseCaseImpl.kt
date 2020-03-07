package id44.mizuki.libraries.account.domain.usecase.fetchownaccounts

import id44.mizuki.shared.model.account.Account
import id44.mizuki.infra.account.repository.AccountRepository

internal class
FetchOwnAccountsUseCaseImpl(
    private val repository: AccountRepository
) : FetchOwnAccountsUseCase {
    override fun execute(): List<Account> = repository.fetchOwnAccounts()
}
