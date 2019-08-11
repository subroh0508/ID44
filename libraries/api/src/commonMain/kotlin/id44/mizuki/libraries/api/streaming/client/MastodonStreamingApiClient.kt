package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.wss
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.serialization.json.Json

internal class MastodonStreamingApiClient(
    private val httpClient: HttpClient,
    private val channel: BroadcastChannel<StreamingEventJson>
) : MastodonStreamingApi {
    override suspend fun openEventChannel(
        hostName: String,
        accessToken: String,
        stream: StreamType
    ): BroadcastChannel<StreamingEventJson> {
        httpClient.wss(
            host = hostName,
            path = "/api/v1/streaming/?stream=${stream.realValue}&access_token=$accessToken"
        ) {
            val frame = incoming.receive()

            when (frame) {
                is Frame.Text -> channel.send(
                    Json.parse(StreamingEventJson.serializer(), frame.readText())
                )
            }
        }

        return channel
    }

    override fun closeEventChannel() {
        httpClient.close()
    }
}
