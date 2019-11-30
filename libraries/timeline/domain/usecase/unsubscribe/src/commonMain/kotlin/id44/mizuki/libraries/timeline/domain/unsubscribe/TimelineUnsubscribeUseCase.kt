package id44.mizuki.libraries.timeline.domain.unsubscribe

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface TimelineUnsubscribeUseCase {
    fun execute(hostName: HostName, id: AccountId, stream: Stream)
}
