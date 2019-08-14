package id44.mizuki.libraries.api.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.PrefKeys
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.AccessTokenStoreClient
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApiClient
import io.ktor.client.HttpClient
import kotlinx.coroutines.channels.BroadcastChannel
import kotlinx.coroutines.channels.Channel.Factory.CONFLATED

@Module
class MastodonStreamingApiModule {
    @Provides
    @ModuleScope
    fun provideAccessTokenStore(app: Application): AccessTokenStore
            = AccessTokenStoreClient(app.getSharedPreferences(PrefKeys.NAME_ACCESS_TOKEN_PREFERENCES, Context.MODE_PRIVATE))

    @Provides
    @ModuleScope
    fun provideMastodonStreamingApi(httpClient: HttpClient): MastodonStreamingApi
            = MastodonStreamingApiClient(httpClient, BroadcastChannel(CONFLATED))
}
