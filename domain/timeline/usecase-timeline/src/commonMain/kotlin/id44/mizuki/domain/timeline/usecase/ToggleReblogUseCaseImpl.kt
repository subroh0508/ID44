package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.infra.status.repository.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ToggleReblogUseCaseImpl(
    private val repository: StatusRepository
) : ToggleReblogUseCase {
    override suspend fun execute(status: Status) = withContext(Dispatchers.Default) {
        if (status.reblogged)
            repository.unreblog(status.id)
        else
            repository.reblog(status.id)
    }
}
