package id44.mizuki.libraries.auth.infra.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepositoryImpl
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepository
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepositoryImpl

@Module
class AuthRepositoryModule {
    @Provides
    @ModuleScope
    fun provideAppCredentialRepository(
            mastodonAuthApi: MastodonAuthApi, appCredentialStore: AppCredentialStore
    ): AppCredentialRepository = AppCredentialRepositoryImpl(mastodonAuthApi, appCredentialStore)

    @Provides
    @ModuleScope
    fun provideAccessTokenRepository(
            mastodonAuthApi: MastodonAuthApi, accessTokenStore: AccessTokenStore
    ): AccessTokenRepository = AccessTokenRepositoryImpl(mastodonAuthApi, accessTokenStore)
}
