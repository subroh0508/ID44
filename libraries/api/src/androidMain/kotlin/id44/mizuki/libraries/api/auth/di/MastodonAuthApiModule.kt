package id44.mizuki.libraries.api.auth.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES
import id44.mizuki.libraries.api.PrefKeys.NAME_APP_CREDENTIAL_PREFERENCES
import id44.mizuki.libraries.api.auth.AuthHttpClientProvider
import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.AppCredentialStoreClient
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.auth.client.MastodonAuthApiClient
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.AccessTokenStoreClient
import io.ktor.client.HttpClient
import io.ktor.client.features.UserAgent
import kotlinx.serialization.json.Json

@Module
class MastodonAuthApiModule {
    @Provides
    @ModuleScope
    fun provideAppCredentialStore(app: Application): AppCredentialStore =
        AppCredentialStoreClient(app.getSharedPreferences(NAME_APP_CREDENTIAL_PREFERENCES, Context.MODE_PRIVATE))

    @Provides
    @ModuleScope
    fun provideAccessTokenStore(app: Application): AccessTokenStore =
        AccessTokenStoreClient(app.getSharedPreferences(NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))

    @Provides
    @ModuleScope
    fun provideMastodonAuthApi(userAgent: UserAgent, json: Json): MastodonAuthApi =
        MastodonAuthApiClient(AuthHttpClientProvider.provide(userAgent, json))
}
