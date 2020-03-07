package id44.mizuki.infra.status.repository

import id44.mizuki.api.streaming.client.MastodonStreamingApi
import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.infra.status.toStatus
import id44.mizuki.infra.status.toStreamType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull

internal class StreamingRepositoryImpl(
    private val streamingApi: MastodonStreamingApi
) : StreamingRepository {
    override suspend fun openSubscription(host: HostName, token: AccessToken, stream: Stream): Flow<Status>? {
        val streamType = stream.toStreamType()
        if (streamingApi.sessionStarted(host, token, streamType)) {
            return null
        }

        return streamingApi.openEventChannel(host, token, streamType)
            .consumeAsFlow()
            .mapNotNull { it.toStatus() }
    }

    override suspend fun closeSubscription(host: HostName, token: AccessToken, stream: Stream) =
            streamingApi.closeEventChannel(host, token, stream.toStreamType())
}
