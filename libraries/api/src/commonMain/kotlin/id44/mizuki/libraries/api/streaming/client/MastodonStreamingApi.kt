package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.coroutines.channels.ReceiveChannel

interface MastodonStreamingApi {
    val host: HostName
    fun streamKey(stream: StreamType): String

    suspend fun openEventChannel(stream: StreamType): ReceiveChannel<StreamingEventJson>

    fun closeEventChannel()
}
