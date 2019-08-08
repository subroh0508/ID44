package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.toStatus
import id44.mizuki.libraries.timeline.infra.toStreamType
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.mapNotNull

internal class StatusRepositoryImpl(
    private val streamingApi: MastodonStreamingApi,
    private val localStore: AccessTokenStore
) : StatusRepository {
    private val channels: HashMap<String, ReceiveChannel<StreamingEventJson>> = hashMapOf()

    override suspend fun receiveStatus(hostName: String, stream: Stream): Status {
        val key = "$hostName/${stream.toStreamType().realValue}"

        val channel = channels[key]
            ?: streamingApi.openEventChannel(
                hostName,
                localStore.getAccessToken(hostName),
                StreamType.USER
            ).also { channels[key] = it }

        return channel.mapNotNull { it.toStatus() }.receive()
    }
}