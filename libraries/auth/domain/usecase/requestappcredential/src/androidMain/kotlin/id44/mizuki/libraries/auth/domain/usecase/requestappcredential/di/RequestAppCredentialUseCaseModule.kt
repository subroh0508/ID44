package id44.mizuki.libraries.auth.domain.usecase.requestappcredential.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCaseImpl
import id44.mizuki.libraries.auth.infra.repository.AccountCredentialRepository
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepository

@Module
class RequestAppCredentialUseCaseModule {
    @Provides
    @ModuleScope
    fun provideRequestAppCredentialUseCase(
        appCredentialRepository: AppCredentialRepository,
        accountCredentialRepository: AccountCredentialRepository
    ): RequestAppCredentialUseCase = RequestAppCredentialUseCaseImpl(appCredentialRepository, accountCredentialRepository)
}
