package id44.mizuki.libraries.auth.infra.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.api.auth.client.AppCredentialStore
import id44.mizuki.libraries.api.auth.client.MastodonAuthApi
import id44.mizuki.libraries.api.client.AccessTokenStore
import id44.mizuki.libraries.api.client.LocalCacheStore
import id44.mizuki.libraries.auth.infra.repository.*

@Module
class AuthRepositoryModule {
    @Provides
    @ModuleScope
    fun provideAppCredentialRepository(
            mastodonAuthApi: MastodonAuthApi,
            appCredentialStore: AppCredentialStore
    ): AppCredentialRepository = AppCredentialRepositoryImpl(mastodonAuthApi, appCredentialStore)

    @Provides
    @ModuleScope
    fun provideAccountCredentialRepository(
            mastodonAuthApi: MastodonAuthApi,
            accessTokenStore: AccessTokenStore,
            localCacheStore: LocalCacheStore
    ): AccountCredentialRepository = AccountCredentialRepositoryImpl(mastodonAuthApi, accessTokenStore, localCacheStore)
}
