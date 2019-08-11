package id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class TimelineUnsubscribeUseCaseModule {
    @Provides
    @ModuleScope
    fun provideTimelineUnsubscribeUseCase(
        repository: StatusRepository
    ): TimelineUnsubscribeUseCase = TimelineUnsubscribeUseCaseImpl(repository)
}
