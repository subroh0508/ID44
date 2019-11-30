package id44.mizuki.libraries.api.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.CredentialProvider
import id44.mizuki.libraries.api.PrefKeys
import id44.mizuki.libraries.api.WebSocketClientProvider
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.AccessTokenStoreClient
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.api.client.LocalCacheStoreClient
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApiClient
import io.ktor.client.features.UserAgent
import kotlinx.serialization.json.Json

@Module
class MastodonStreamingApiModule {
    @Provides
    @ModuleScope
    fun provideAccessTokenStore(app: Application): AccessTokenStore =
        AccessTokenStoreClient(app.getSharedPreferences(PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))

    @Provides
    @ModuleScope
    fun provideLocalCacheStore(app: Application, json: Json): LocalCacheStore =
        LocalCacheStoreClient(app.getSharedPreferences(PrefKeys.NAME_CACHE_PREFERENCES, Context.MODE_PRIVATE), json)

    @Provides
    @ModuleScope
    fun provideMastodonStreamingApi(userAgent: UserAgent, provider: CredentialProvider, json: Json): MastodonStreamingApi =
        MastodonStreamingApiClient(WebSocketClientProvider.provide(userAgent), provider, json)
}
