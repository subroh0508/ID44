package id44.mizuki.domain.timeline.usecase

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
