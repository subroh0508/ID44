package id44.mizuki.libraries.timeline.domain.usecase.togglereblog

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
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
