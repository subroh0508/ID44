package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.wss
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.serialization.json.Json

internal class MastodonStreamingApiClient(
    private val httpClient: HttpClient
) : MastodonStreamingApi {
    override suspend fun openEventChannel(
        hostName: String,
        accessToken: String,
        stream: StreamType
    ): ReceiveChannel<StreamingEventJson> = GlobalScope.produce(
        Dispatchers.Unconfined, capacity = Channel.UNLIMITED
    ) {
        httpClient.wss(
            host = hostName,
            path = "/api/v1/streaming/?stream=${stream.realValue}&access_token=$accessToken"
        ) {
            for (frame in incoming) {
                when (frame) {
                    is Frame.Text -> {
                        val text = frame.readText()
                        val json = Json.parse(StreamingEventJson.serializer(), text)

                        println(text)
                        @UseExperimental(ExperimentalCoroutinesApi::class)
                        send(json)
                    }
                }
            }
        }
    }

    override fun closeEventChannel() {
        httpClient.close()
    }
}
