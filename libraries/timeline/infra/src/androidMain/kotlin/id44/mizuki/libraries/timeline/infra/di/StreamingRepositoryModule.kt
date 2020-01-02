package id44.mizuki.libraries.timeline.infra.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.streaming.client.MastodonStreamingApi
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepository
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepositoryImpl

@Module
class StreamingRepositoryModule {
    @Provides
    @ModuleScope
    fun provideStreamingRepository(
        streamingApi: MastodonStreamingApi
    ): StreamingRepository = StreamingRepositoryImpl(streamingApi)
}
