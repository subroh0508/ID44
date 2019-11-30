package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.Flow

interface StatusRepository {
    suspend fun openSubscription(hostName: HostName, id: AccountId, stream: Stream): Flow<Status>

    fun closeSubscription(hostName: HostName, id: AccountId, stream: Stream)
}
