package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.toStatus
import id44.mizuki.libraries.timeline.infra.toStreamType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull

internal class StatusRepositoryImpl(
    private val streamingApi: MastodonStreamingApi
) : StatusRepository {
    private val channels: HashMap<String, Flow<Status>> = hashMapOf()

    override suspend fun openSubscription(stream: Stream): Flow<Status> {
        val streamType = stream.toStreamType()
        val key = streamingApi.streamKey(streamType)

        return channels[key] ?: streamingApi.openEventChannel(streamType)
            .consumeAsFlow()
            .mapNotNull { it.toStatus() }
            .also { channels[key] = it }
    }

    override fun closeSubscription(stream: Stream) {
        channels.remove(streamingApi.streamKey(stream.toStreamType()))
    }
}
