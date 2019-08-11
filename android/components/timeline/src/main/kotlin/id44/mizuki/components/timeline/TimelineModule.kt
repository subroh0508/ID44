package id44.mizuki.components.timeline

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.di.MastodonStreamingApiModule
import id44.mizuki.libraries.timeline.domain.subscribe.di.TimelineSubscribeUseCaseModule
import id44.mizuki.libraries.timeline.domain.unsubscribe.di.TimelineUnsubscribeUseCaseModule
import id44.mizuki.libraries.timeline.infra.di.StatusRepositoryModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.websocket.WebSockets
import io.ktor.http.userAgent
import okhttp3.logging.HttpLoggingInterceptor

@Module(includes = [
    MastodonStreamingApiModule::class,
    StatusRepositoryModule::class,
    TimelineSubscribeUseCaseModule::class,
    TimelineUnsubscribeUseCaseModule::class
])
class TimelineModule {
    @Provides
    @ModuleScope
    fun provideHttpClient(userAgent: UserAgent): HttpClient = HttpClient(OkHttp) {
        engine {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                addInterceptor(loggingInterceptor)
            }
        }
        install(WebSockets)
        install(DefaultRequest) {
            userAgent(userAgent.agent)
        }
    }
}
