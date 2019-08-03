package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import kotlinx.coroutines.channels.ReceiveChannel

interface MastodonStreamingApi {
    fun openEventChannel(
        hostName: String,
        accessToken: String,
        stream: StreamType
    ): ReceiveChannel<StreamingEventJson>

    fun closeEventChannel()
}
