package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.ws
import io.ktor.http.HttpMethod
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.channels.produce
import kotlinx.serialization.json.Json

internal class MastodonStreamingApiClient(
    private val httpClient: HttpClient
) : MastodonStreamingApi {
    override fun openEventChannel(
        hostName: String,
        accessToken: String,
        stream: StreamType
    ): ReceiveChannel<StreamingEventJson> = GlobalScope.produce {
        httpClient.ws(
            method = HttpMethod.Get,
            host = hostName,
            path = "/api/v1/streaming?access_token=$accessToken&stream=${stream.realValue}"
        ) {
            val frame = incoming.receive()

            when (frame) {
                is Frame.Text -> this@produce.send(
                    Json.parse(StreamingEventJson.serializer(), frame.readText())
                )
            }
        }
    }

    override fun closeEventChannel() {
        httpClient.close()
    }
}
