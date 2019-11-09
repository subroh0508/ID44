package id44.mizuki.libraries.timeline.infra.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
import id44.mizuki.libraries.timeline.infra.repository.StatusRepositoryImpl

@Module
class StatusRepositoryModule {
    @Provides
    @ModuleScope
    fun provideStatusRepository(
        streamingApi: MastodonStreamingApi,
        localStore: AccessTokenStore
    ): StatusRepository = StatusRepositoryImpl(streamingApi, localStore)
}
