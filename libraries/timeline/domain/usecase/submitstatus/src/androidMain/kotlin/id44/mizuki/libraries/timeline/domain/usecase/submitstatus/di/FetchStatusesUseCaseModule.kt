package id44.mizuki.libraries.timeline.domain.usecase.submitstatus.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.SubmitStatusUseCase
import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.SubmitStatusUseCaseImpl
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository

@Module
class FetchStatusesUseCaseModule {
    @Provides
    @ModuleScope
    internal fun provideFetchStatusesUseCase(
        repository: StatusRepository
    ): SubmitStatusUseCase = SubmitStatusUseCaseImpl(repository)
}
