package id44.mizuki.libraries.timeline.domain.usecase.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.TimelineSubscribeUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class TimelineSubscribeUseCaseModule {
    @Provides
    @ModuleScope
    fun provideTimelineSubscribeUseCase(
        repository: StatusRepository
    ): TimelineSubscribeUseCase = TimelineSubscribeUseCaseImpl(repository)
}
