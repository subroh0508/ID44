package id44.mizuki.libraries.timeline.domain.usecase.togglereblog.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.ToggleReblogUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.ToggleReblogUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class ToggleReblogUseCaseModule {
    @Provides
    @ModuleScope
    internal fun provideFetchStatusesUseCase(
        repository: StatusRepository
    ): ToggleReblogUseCase = ToggleReblogUseCaseImpl(repository)
}
