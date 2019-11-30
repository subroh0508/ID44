package id44.mizuki.libraries.timeline.domain.unsubscribe

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

internal class TimelineUnsubscribeUseCaseImpl(
    private val repository: StatusRepository
) : TimelineUnsubscribeUseCase {
    override fun execute(hostName: HostName, id: AccountId, stream: Stream) =
        repository.closeSubscription(hostName, id, stream)
}
