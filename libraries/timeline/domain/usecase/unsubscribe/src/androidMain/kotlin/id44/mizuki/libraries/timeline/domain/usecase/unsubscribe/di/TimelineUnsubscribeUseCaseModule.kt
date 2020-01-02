package id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepository

@Module
class TimelineUnsubscribeUseCaseModule {
    @Provides
    @ModuleScope
    fun provideTimelineUnsubscribeUseCase(
        repository: StreamingRepository,
        accessTokenRepository: AccessTokenRepository
    ): TimelineUnsubscribeUseCase = TimelineUnsubscribeUseCaseImpl(repository, accessTokenRepository)
}
