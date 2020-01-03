package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.Flow

interface StreamingRepository {
    suspend fun openSubscription(host: HostName, token: AccessToken, stream: Stream): Flow<Status>?

    suspend fun closeSubscription(host: HostName, token: AccessToken, stream: Stream)
}