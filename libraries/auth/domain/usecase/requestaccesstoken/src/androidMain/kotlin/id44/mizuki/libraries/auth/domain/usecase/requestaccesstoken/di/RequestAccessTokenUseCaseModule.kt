package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCaseImpl
import id44.mizuki.libraries.auth.infra.repository.AccountCredentialRepository
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepository

@Module
class RequestAccessTokenUseCaseModule {
    @Provides
    @ModuleScope
    fun provideRequestAppCredentialUseCase(
        appCredentialRepository: AppCredentialRepository,
        accountCredentialRepository: AccountCredentialRepository
    ): RequestAccessTokenUseCase = RequestAccessTokenUseCaseImpl(appCredentialRepository, accountCredentialRepository)
}
