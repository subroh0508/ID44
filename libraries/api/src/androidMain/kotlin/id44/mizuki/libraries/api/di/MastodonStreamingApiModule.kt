package id44.mizuki.libraries.api.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.CredentialProvider
import id44.mizuki.libraries.api.WebSocketClientProvider
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApiClient
import io.ktor.client.features.UserAgent
import kotlinx.serialization.json.Json

@Module
class MastodonStreamingApiModule {
    @Provides
    @ModuleScope
    fun provideMastodonStreamingApi(userAgent: UserAgent, provider: CredentialProvider, json: Json): MastodonStreamingApi =
        MastodonStreamingApiClient(WebSocketClientProvider.provide(userAgent), provider, json)
}
