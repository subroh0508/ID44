package id44.mizuki.libraries.timeline.domain.usecase

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

internal class TimelineSubscribeUseCaseImpl(
    private val repository: StatusRepository
) : TimelineSubscribeUseCase {
    override suspend fun execute(hostName: String, stream: Stream): Status
            = repository.openSubscription(hostName, stream).receive()
}