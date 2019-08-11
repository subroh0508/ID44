package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import kotlinx.coroutines.channels.BroadcastChannel

interface MastodonStreamingApi {
    suspend fun openEventChannel(
        hostName: String,
        accessToken: String,
        stream: StreamType
    ): BroadcastChannel<StreamingEventJson>

    fun closeEventChannel()
}
