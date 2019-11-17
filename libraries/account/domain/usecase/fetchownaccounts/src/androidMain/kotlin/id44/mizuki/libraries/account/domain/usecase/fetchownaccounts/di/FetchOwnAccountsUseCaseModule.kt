package id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCaseImpl
import id44.mizuki.libraries.account.infra.repository.AccountRepository

@Module
class FetchOwnAccountsUseCaseModule {
    @Provides
    @ModuleScope
    fun provideFetchOwnAccountsUseCase(
        repository: AccountRepository
    ): FetchOwnAccountsUseCase = FetchOwnAccountsUseCaseImpl(repository)
}
