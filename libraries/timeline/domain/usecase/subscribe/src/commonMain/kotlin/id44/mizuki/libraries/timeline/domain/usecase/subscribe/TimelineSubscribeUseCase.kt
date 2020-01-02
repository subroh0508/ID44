package id44.mizuki.libraries.timeline.domain.usecase.subscribe

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.Flow

interface TimelineSubscribeUseCase {
    suspend fun execute(host: HostName, accountId: AccountId, stream: Stream): Flow<Status>?
}
