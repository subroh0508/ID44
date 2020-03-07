package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.streaming.StreamType
import id44.mizuki.libraries.api.streaming.json.StreamingEventJson
import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.HostName
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.serialization.json.Json

internal class MastodonStreamingApiClient(
    private val httpClient: HttpClient,
    private val json: Json
) : MastodonStreamingApi {
    private fun streamKey(host: HostName, token: AccessToken, stream: StreamType) =
        listOf(host.value, token.value, stream.realValue).joinToString("/")

    private val sessions: HashMap<String, WebSocketSession> = hashMapOf()

    override fun sessionStarted(host: HostName, token: AccessToken, stream: StreamType) = sessions.containsKey(streamKey(host, token, stream))

    override suspend fun openEventChannel(host: HostName, token: AccessToken, stream: StreamType): ReceiveChannel<StreamingEventJson> {
        val session = startWebsocketSession(host, token, stream).also {
            sessions[streamKey(host, token, stream)] = it
        }

        return Channel<StreamingEventJson>(Channel.UNLIMITED).apply {
            sendStreamingEvent(session)
        }
    }

    override suspend fun closeEventChannel(host: HostName, token: AccessToken, stream: StreamType) {
        sessions.remove(streamKey(host, token, stream))?.cancel()
    }

    private suspend fun startWebsocketSession(host: HostName, token: AccessToken, stream: StreamType): WebSocketSession =
        httpClient.webSocketSession(
            host = host.value,
            path = "/api/v1/streaming/?stream=${stream.realValue}&access_token=${token.value}"
        )

    private fun Channel<StreamingEventJson>.sendStreamingEvent(
        session: WebSocketSession
    ) = CoroutineScope(Dispatchers.Default).launch {
        for (frame in session.incoming) {
            when (frame) {
                is Frame.Text -> {
                    val text = frame.readText()

                    println(text)
                    @UseExperimental(ExperimentalCoroutinesApi::class)
                    send(json.parse(StreamingEventJson.serializer(), text))
                }
            }
        }
    }
}
