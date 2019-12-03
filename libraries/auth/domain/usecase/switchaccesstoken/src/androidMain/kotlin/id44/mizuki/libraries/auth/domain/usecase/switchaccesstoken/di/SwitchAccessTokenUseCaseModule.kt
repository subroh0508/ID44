package id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCaseImpl
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository

@Module
class SwitchAccessTokenUseCaseModule {
    @Provides
    @ModuleScope
    fun provideFetchOwnAccountUseCase(
        repository: AccessTokenRepository
    ): SwitchAccessTokenUseCase = SwitchAccessTokenUseCaseImpl(repository)
}
