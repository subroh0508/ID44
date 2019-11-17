package id44.mizuki.libraries.account.domain.usecase.fetchownaccount

import id44.mizuki.libraries.account.infra.repository.AccountRepository
import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchOwnAccountUseCaseImpl(
    private val repository: AccountRepository
) : FetchOwnAccountUseCase {
    override suspend fun execute(hostName: HostName) = withContext(Dispatchers.Default) {
        repository.fetchOwnAccount(hostName)
    }
}
