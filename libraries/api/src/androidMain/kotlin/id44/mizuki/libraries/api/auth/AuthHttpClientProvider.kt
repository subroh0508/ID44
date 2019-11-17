package id44.mizuki.libraries.api.auth

import id44.mizuki.libraries.api.BuildConfig
import id44.mizuki.libraries.api.auth.model.AccessToken
import id44.mizuki.libraries.api.auth.model.AppCredential
import id44.mizuki.libraries.api.auth.params.PostApps
import id44.mizuki.libraries.api.auth.params.PostOauthToken
import id44.mizuki.libraries.api.params.GetAppsVerifyCredential
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.URLProtocol
import io.ktor.http.userAgent
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

internal object AuthHttpClientProvider {
    fun provide(userAgent: UserAgent, json: Json): HttpClient = HttpClient(OkHttp) {
        engine {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
        }
        install(DefaultRequest) {
            userAgent(userAgent.agent)
            url { protocol = URLProtocol.HTTPS }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json).apply {
                setMapper(PostApps.Request::class, PostApps.Request.serializer())
                setMapper(PostOauthToken.Request::class, PostOauthToken.Request.serializer())
                setMapper(AccessToken::class, AccessToken.serializer())
                setMapper(AppCredential::class, AppCredential.serializer())
                setMapper(GetAppsVerifyCredential.Response::class, GetAppsVerifyCredential.Response.serializer())
            }
        }
    }
}
