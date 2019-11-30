package id44.mizuki.libraries.api.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.CredentialProvider
import id44.mizuki.libraries.api.PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES
import id44.mizuki.libraries.api.PrefKeys.NAME_CACHE_PREFERENCES
import id44.mizuki.libraries.api.client.*
import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

@Module
class MastodonApiModule {
    @Provides
    @ModuleScope
    fun provideAccessTokenStore(app: Application): AccessTokenStore =
        AccessTokenStoreClient(app.getSharedPreferences(NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))

    @Provides
    @ModuleScope
    fun provideLocalCacheStore(app: Application, json: Json): LocalCacheStore =
        LocalCacheStoreClient(app.getSharedPreferences(NAME_CACHE_PREFERENCES, Context.MODE_PRIVATE), json)

    @Provides
    @ModuleScope
    fun provideMastodonApi(httpClient: HttpClient, provider: CredentialProvider): MastodonApi = MastodonApiClient(httpClient, provider)
}
