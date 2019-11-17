package id44.mizuki.components.authentication

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.auth.di.MastodonAuthApiModule
import id44.mizuki.libraries.api.auth.model.AccessToken
import id44.mizuki.libraries.api.auth.model.AppCredential
import id44.mizuki.libraries.api.auth.params.PostApps
import id44.mizuki.libraries.api.auth.params.PostOauthToken
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.di.RequestAccessTokenUseCaseModule
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.di.RequestAppCredentialUseCaseModule
import id44.mizuki.libraries.auth.infra.di.AuthRepositoryModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.http.userAgent
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

@Module(includes = [
    MastodonAuthApiModule::class,
    AuthRepositoryModule::class,
    RequestAppCredentialUseCaseModule::class,
    RequestAccessTokenUseCaseModule::class
])
class AuthModule {
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
        install(DefaultRequest) {
            userAgent(userAgent.agent)
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(json).apply {
                setMapper(PostApps.Request::class, PostApps.Request.serializer())
                setMapper(PostOauthToken.Request::class, PostOauthToken.Request.serializer())
                setMapper(AccessToken::class, AccessToken.serializer())
                setMapper(AppCredential::class, AppCredential.serializer())
            }
        }
    }
}
