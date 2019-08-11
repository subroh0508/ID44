package id44.mizuki.libraries.timeline.domain.usecase.unsubscribe

import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

internal class TimelineUnsubscribeUseCaseImpl(
    private val repository: StatusRepository
) : TimelineUnsubscribeUseCase {
    override fun execute(hostName: String, stream: Stream) {
        repository.closeSubscription(hostName, stream)
    }
}
