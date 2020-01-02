package id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.di

import dagger.Binds
import dagger.Module
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCase
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCaseImpl

@Module
abstract class FetchStatusesUseCaseModule {
    @Binds
    @ModuleScope
    internal abstract fun bindFetchStatusesUseCase(useCase: FetchStatusesUseCaseImpl): FetchStatusesUseCase
}
