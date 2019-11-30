package id44.mizuki.libraries.auth.infra.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.CredentialProvider
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepositoryImpl

@Module
class AccessTokenRepositoryModule {
    @Provides
    @ModuleScope
    fun provideCredentialProvider(
        repository: AccessTokenRepository
    ): CredentialProvider = repository as AccessTokenRepositoryImpl

    @Provides
    @ModuleScope
    internal fun provideAccessTokenRepository(
        authLocalStore: AccessTokenStore,
        localStore: LocalCacheStore
    ): AccessTokenRepository = AccessTokenRepositoryImpl(authLocalStore, localStore)
}
