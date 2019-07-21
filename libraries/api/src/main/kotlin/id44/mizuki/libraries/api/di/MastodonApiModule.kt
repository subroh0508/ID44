package id44.mizuki.libraries.api.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.HostScope
import id44.mizuki.libraries.api.PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.AccessTokenStoreClient
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.libraries.api.client.MastodonApiClient
import io.ktor.client.HttpClient
import io.ktor.client.features.json.JsonSerializer

@Module
class MastodonApiModule {
    @Provides
    @HostScope
    fun provideAccessTokenStore(app: Application): AccessTokenStore
            = AccessTokenStoreClient(app.getSharedPreferences(NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))

    @Provides
    @HostScope
    fun provideMastodonApi(httpClient: HttpClient, json: JsonSerializer): MastodonApi
            = MastodonApiClient(httpClient, json)
}
