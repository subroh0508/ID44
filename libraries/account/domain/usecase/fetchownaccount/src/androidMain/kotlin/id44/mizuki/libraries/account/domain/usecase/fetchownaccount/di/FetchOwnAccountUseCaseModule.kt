package id44.mizuki.libraries.account.domain.usecase.fetchownaccount.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCaseImpl
import id44.mizuki.libraries.account.infra.repository.AccountRepository

@Module
class FetchOwnAccountUseCaseModule {
    @Provides
    @ModuleScope
    fun provideFetchOwnAccountUseCase(
        repository: AccountRepository
    ): FetchOwnAccountUseCase = FetchOwnAccountUseCaseImpl(repository)
}
