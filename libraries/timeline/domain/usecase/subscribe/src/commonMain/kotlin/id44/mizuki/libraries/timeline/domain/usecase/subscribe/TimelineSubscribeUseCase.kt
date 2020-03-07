package id44.mizuki.libraries.timeline.domain.usecase.subscribe

import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream
import kotlinx.coroutines.flow.Flow

interface TimelineSubscribeUseCase {
    suspend fun execute(host: HostName, accountId: AccountId, stream: Stream): Flow<Status>?
}
