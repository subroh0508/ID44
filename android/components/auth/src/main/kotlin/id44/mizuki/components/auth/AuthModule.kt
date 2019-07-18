package id44.mizuki.components.auth

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.di.MastodonAuthApiModule
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.di.RequestAccessTokenUseCaseModule
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.di.RequestAppCredentialUseCaseModule
import id44.mizuki.libraries.auth.infra.di.AuthRepositoryModule
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.DefaultRequest
import io.ktor.client.features.UserAgent
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.JsonSerializer
import io.ktor.http.userAgent
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
    fun provideHttpClient(userAgent: UserAgent, json: JsonSerializer): HttpClient = HttpClient(OkHttp) {
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
            serializer = json
        }
    }
}
