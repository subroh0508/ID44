package id44.mizuki.libraries.timeline.domain.unsubscribe.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class TimelineUnsubscribeUseCaseModule {
    @Provides
    @ModuleScope
    fun provideTimelineUnsubscribeUseCase(
        repository: StatusRepository,
        accessTokenRepository: AccessTokenRepository
    ): TimelineUnsubscribeUseCase = TimelineUnsubscribeUseCaseImpl(repository, accessTokenRepository)
}
