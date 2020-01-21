package id44.mizuki.libraries.timeline.domain.usecase.togglefavourite

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
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
