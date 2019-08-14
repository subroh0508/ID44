package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.Flow

interface StatusRepository {
    suspend fun openSubscription(hostName: String, stream: Stream): Flow<Status>

    fun closeSubscription(hostName: String, stream: Stream)
}
