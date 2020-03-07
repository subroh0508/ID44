package id44.mizuki.libraries.api

import id44.mizuki.shared.util.valueobject.HostName
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.URLProtocol
import io.ktor.http.userAgent
import io.ktor.util.KtorExperimentalAPI
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

class HttpsClientProvider(
    private val mapper: KotlinxSerializer.() -> Unit
) {
    internal fun provide(userAgent: UserAgent, json: Json) = HttpClient(OkHttp) {
        engine {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
        }
        @UseExperimental(KtorExperimentalAPI::class)
        install(DefaultRequest) {
            url { protocol = URLProtocol.HTTPS }
            userAgent(userAgent.agent)
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json).apply(mapper)
        }
    }
}
