package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.api.client.AccessTokenStore
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

    override suspend fun openSubscription(hostName: String, stream: Stream): ReceiveChannel<Status> {
        val key = hostName.generateKey(stream)

        val channel = channels[key]
            ?: streamingApi.openEventChannel(
                hostName,
                localStore.getAccessToken(hostName),
                stream.toStreamType()
            ).openSubscription().also {
                channels[key] = it
            }

        return channel.mapNotNull { it.toStatus() }
    }

    override fun closeSubscription(hostName: String, stream: Stream) {
        val key = hostName.generateKey(stream)

        channels[key]?.cancel()
        channels.remove(key)
    }

    private fun String.generateKey(stream: Stream) = "$this/${stream.toStreamType().realValue}"
}
