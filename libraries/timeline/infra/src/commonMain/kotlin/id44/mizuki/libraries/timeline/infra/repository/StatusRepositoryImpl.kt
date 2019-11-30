package id44.mizuki.libraries.timeline.infra.repository

import id44.mizuki.libraries.api.TokenExpiredException
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
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

    override suspend fun openSubscription(hostName: HostName, id: AccountId, stream: Stream): Flow<Status> {
        val accessToken = localStore.getAccessToken(id.value) ?: throw TokenExpiredException(hostName, id)
        val key = genKey(hostName, id, stream)

        return channels[key] ?: streamingApi.openEventChannel(
            hostName.value, accessToken, stream.toStreamType()
        ).consumeAsFlow()
            .mapNotNull { it.toStatus() }
            .also { channels[key] = it }
    }

    override fun closeSubscription(hostName: HostName, id: AccountId, stream: Stream) {
        channels.remove(genKey(hostName, id, stream))
    }

    private fun genKey(host: HostName, id: AccountId, stream: Stream) =
        listOf(host.value, id.value, stream.toStreamType().realValue).joinToString("/")
}
