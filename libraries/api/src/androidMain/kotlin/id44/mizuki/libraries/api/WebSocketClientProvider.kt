package id44.mizuki.libraries.api

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.websocket.WebSockets
import io.ktor.http.URLProtocol
import io.ktor.http.userAgent
import io.ktor.util.KtorExperimentalAPI
import okhttp3.logging.HttpLoggingInterceptor

internal object WebSocketClientProvider {
    fun provide(userAgent: UserAgent): HttpClient = HttpClient(OkHttp) {
        engine {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
        }
        @UseExperimental(KtorExperimentalAPI::class)
        install(WebSockets)
        install(DefaultRequest) {
            url { protocol = URLProtocol.WSS }
            userAgent(userAgent.agent)
        }
    }
}
