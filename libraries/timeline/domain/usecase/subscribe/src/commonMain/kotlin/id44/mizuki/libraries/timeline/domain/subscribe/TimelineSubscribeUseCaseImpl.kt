package id44.mizuki.libraries.timeline.domain.subscribe

import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

internal class TimelineSubscribeUseCaseImpl(
    private val repository: StatusRepository
) : TimelineSubscribeUseCase {
    override suspend fun execute(stream: Stream) =
        repository.openSubscription(stream)
}
