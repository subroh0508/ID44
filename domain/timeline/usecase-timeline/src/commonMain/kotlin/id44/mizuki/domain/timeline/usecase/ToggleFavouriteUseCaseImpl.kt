package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.infra.status.repository.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class ToggleFavouriteUseCaseImpl(
    private val repository: StatusRepository
) : ToggleFavouriteUseCase {
    override suspend fun execute(status: Status) = withContext(Dispatchers.Default) {
        if (status.favourited)
            repository.unfavourite(status.id)
        else
            repository.favourite(status.id)
    }
}
