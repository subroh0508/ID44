package id44.mizuki.libraries.timeline.domain.usecase.unsubscribe

import id44.mizuki.shared.valueobject.AccountId
import id44.mizuki.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface TimelineUnsubscribeUseCase {
    suspend fun execute(host: HostName, accountId: AccountId, stream: Stream)
}
