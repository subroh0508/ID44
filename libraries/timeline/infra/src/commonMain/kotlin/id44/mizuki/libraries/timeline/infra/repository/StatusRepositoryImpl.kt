package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.toStatus
import id44.mizuki.libraries.timeline.infra.toStreamType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.flow.mapNotNull

internal class StatusRepositoryImpl(
    private val streamingApi: MastodonStreamingApi,
    private val localStore: AccessTokenStore
) : StatusRepository {
    private val channels: HashMap<String, Flow<Status>> = hashMapOf()

    override suspend fun openSubscription(hostName: String, stream: Stream): Flow<Status> {
        val key = hostName.generateKey(stream)

        return channels[key] ?: streamingApi.openEventChannel(
            hostName,
            localStore.getAccessToken(hostName),
            stream.toStreamType()
        ).consumeAsFlow()
            .mapNotNull { it.toStatus() }
            .also { channels[key] = it }
    }

    override fun closeSubscription(hostName: String, stream: Stream) {
        val key = hostName.generateKey(stream)

        channels.remove(key)
    }

    private fun String.generateKey(stream: Stream) = "$this/${stream.toStreamType().realValue}"
}
