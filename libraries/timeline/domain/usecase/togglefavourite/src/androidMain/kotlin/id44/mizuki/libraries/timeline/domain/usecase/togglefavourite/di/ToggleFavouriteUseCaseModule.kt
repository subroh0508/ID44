package id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.ToggleFavouriteUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.ToggleFavouriteUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class ToggleFavouriteUseCaseModule {
    @Provides
    @ModuleScope
    internal fun provideFetchStatusesUseCase(
        repository: StatusRepository
    ): ToggleFavouriteUseCase = ToggleFavouriteUseCaseImpl(repository)
}
