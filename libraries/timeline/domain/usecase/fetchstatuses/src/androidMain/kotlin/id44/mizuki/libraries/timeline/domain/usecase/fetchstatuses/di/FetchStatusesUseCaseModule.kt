package id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCase
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class FetchStatusesUseCaseModule {
    @Provides
    @ModuleScope
    internal fun provideFetchStatusesUseCase(
        repository: StatusRepository
    ): FetchStatusesUseCase = FetchStatusesUseCaseImpl(repository)
}
