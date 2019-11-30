package id44.mizuki.libraries.timeline.domain.unsubscribe

import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

internal class TimelineUnsubscribeUseCaseImpl(
    private val repository: StatusRepository
) : TimelineUnsubscribeUseCase {
    override fun execute(stream: Stream) = repository.closeSubscription(stream)
}
