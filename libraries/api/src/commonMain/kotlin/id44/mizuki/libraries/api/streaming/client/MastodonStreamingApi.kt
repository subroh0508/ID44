package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import id44.mizuki.shared.valueobject.AccessToken
import id44.mizuki.shared.valueobject.HostName
import kotlinx.coroutines.channels.ReceiveChannel

interface MastodonStreamingApi {
    fun sessionStarted(host: HostName, token: AccessToken, stream: StreamType): Boolean

    suspend fun openEventChannel(host: HostName, token: AccessToken, stream: StreamType): ReceiveChannel<StreamingEventJson>

    suspend fun closeEventChannel(host: HostName, token: AccessToken, stream: StreamType)
}
