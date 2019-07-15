package id44.mizuki.auth

import android.content.Context
import id44.mizuki.auth.presenter.AuthenticationPresenter
import id44.mizuki.auth.ui.AuthenticationActivity
import id44.mizuki.libraries.api.auth.client.MastodonAuthApiClient
import id44.mizuki.libraries.api.client.AccessTokenStoreClient
import id44.mizuki.libraries.api.client.AppCredentialStoreClient
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCaseImpl
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCaseImpl
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepositoryImpl
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepositoryImpl
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import okhttp3.logging.HttpLoggingInterceptor

fun generatePresenter(
        activity: AuthenticationActivity
): AuthenticationConstract.Presenter {
    val httpClient = HttpClient(OkHttp) {
        engine {
            if (BuildConfig.DEBUG) {
                val loggingInterceptor = HttpLoggingInterceptor()
                loggingInterceptor.level = HttpLoggingInterceptor.Level.HEADERS
                addInterceptor(loggingInterceptor)
            }
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(Json.nonstrict)
        }
    }

    val client = MastodonAuthApiClient(httpClient)
    val appCredentialLocalStore = AppCredentialStoreClient(activity.getSharedPreferences("app_credential", Context.MODE_PRIVATE))
    val accessTokenLocalStoreClient = AccessTokenStoreClient(activity.getSharedPreferences("access_token", Context.MODE_PRIVATE))

    val appCredentialRepository = AppCredentialRepositoryImpl(client, appCredentialLocalStore)
    val accessTokenRepository = AccessTokenRepositoryImpl(client, accessTokenLocalStoreClient)

    val requestAppCredentialUseCase = RequestAppCredentialUseCaseImpl(appCredentialRepository, accessTokenRepository)
    val requestAccessTokenUseCase = RequestAccessTokenUseCaseImpl(appCredentialRepository, accessTokenRepository)

    return AuthenticationPresenter(activity, requestAppCredentialUseCase, requestAccessTokenUseCase)
}