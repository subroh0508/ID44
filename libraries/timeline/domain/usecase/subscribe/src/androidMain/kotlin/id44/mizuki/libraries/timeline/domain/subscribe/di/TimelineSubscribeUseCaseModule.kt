package id44.mizuki.libraries.timeline.domain.subscribe.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepository

@Module
class TimelineSubscribeUseCaseModule {
    @Provides
    @ModuleScope
    fun provideTimelineSubscribeUseCase(
        repository: StreamingRepository,
        accessTokenRepository: AccessTokenRepository
    ): TimelineSubscribeUseCase = TimelineSubscribeUseCaseImpl(repository, accessTokenRepository)
}
