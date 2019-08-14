package id44.mizuki.libraries.timeline.domain.subscribe

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
import kotlinx.coroutines.flow.Flow

internal class TimelineSubscribeUseCaseImpl(
    private val repository: StatusRepository
) : TimelineSubscribeUseCase {
    override suspend fun execute(hostName: String, stream: Stream): Flow<Status>
            = repository.openSubscription(hostName, stream)
}