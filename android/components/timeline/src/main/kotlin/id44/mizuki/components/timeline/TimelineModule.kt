package id44.mizuki.components.timeline

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.di.FetchOwnAccountsUseCaseModule
import id44.mizuki.libraries.account.infra.di.AccountRepositoryModule
import id44.mizuki.libraries.api.di.MastodonApiModule
import id44.mizuki.libraries.api.di.MastodonStreamingApiModule
import id44.mizuki.libraries.auth.infra.di.AccessTokenRepositoryModule
import id44.mizuki.libraries.timeline.domain.subscribe.di.TimelineSubscribeUseCaseModule
import id44.mizuki.libraries.timeline.domain.unsubscribe.di.TimelineUnsubscribeUseCaseModule
import id44.mizuki.libraries.timeline.infra.di.StatusRepositoryModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.http.userAgent
import io.ktor.util.KtorExperimentalAPI
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

@Module(includes = [
    MastodonApiModule::class,
    MastodonStreamingApiModule::class,
    AccessTokenRepositoryModule::class,

    AccountRepositoryModule::class,
    FetchOwnAccountsUseCaseModule::class,

    StatusRepositoryModule::class,
    TimelineSubscribeUseCaseModule::class,
    TimelineUnsubscribeUseCaseModule::class
])
class TimelineModule {
    @Provides
    @ModuleScope
    fun provideHttpClient(userAgent: UserAgent, json: Json): HttpClient = HttpClient(OkHttp) {
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
            contentType(ContentType.Application.Json)
            userAgent(userAgent.agent)
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
        }
    }
}
