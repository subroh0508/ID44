package id44.mizuki.libraries.account.domain.usecase.fetchownaccount

import id44.mizuki.infra.account.repository.AccountRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchOwnAccountUseCaseImpl(
    private val repository: AccountRepository
) : FetchOwnAccountUseCase {
    override suspend fun execute() = withContext(Dispatchers.Default) {
        repository.fetchOwnAccount()
    }
}
