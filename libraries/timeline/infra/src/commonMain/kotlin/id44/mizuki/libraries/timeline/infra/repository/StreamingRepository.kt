package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream
import kotlinx.coroutines.flow.Flow

interface StreamingRepository {
    suspend fun openSubscription(host: HostName, token: AccessToken, stream: Stream): Flow<Status>?

    suspend fun closeSubscription(host: HostName, token: AccessToken, stream: Stream)
}
