package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.model.status.Stream

interface TimelineUnsubscribeUseCase {
    suspend fun execute(host: HostName, accountId: AccountId, stream: Stream)
}
