package id44.mizuki.libraries.api.streaming.client

import id44.mizuki.libraries.api.CredentialProvider
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
    private val httpClient: HttpClient,
    private val provider: CredentialProvider,
    private val json: Json
) : MastodonStreamingApi {
    override val host get() = provider.nowHost
    override fun streamKey(stream: StreamType) =
        listOf(host.value, provider.nowToken.value, stream.realValue).joinToString("/")

    override suspend fun openEventChannel(stream: StreamType): ReceiveChannel<StreamingEventJson> = GlobalScope.produce(
        Dispatchers.Unconfined, capacity = Channel.UNLIMITED
    ) {
        httpClient.wss(
            host = host.value,
            path = "/api/v1/streaming/?stream=${stream.realValue}&access_token=${provider.nowToken.value}"
        ) {
            for (frame in incoming) {
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

    override fun closeEventChannel() {
        httpClient.close()
    }
}
