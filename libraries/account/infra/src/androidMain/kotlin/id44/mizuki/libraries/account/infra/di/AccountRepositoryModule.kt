package id44.mizuki.libraries.account.infra.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.account.infra.repository.AccountRepository
import id44.mizuki.libraries.account.infra.repository.AccountRepositoryImpl
import id44.mizuki.libraries.api.client.MastodonApi
import id44.mizuki.libraries.api.client.LocalCacheStore

@Module
class AccountRepositoryModule {
    @Provides
    @ModuleScope
    fun provideAccountRepository(
        mastodonApi: MastodonApi, localCacheStore: LocalCacheStore
    ): AccountRepository = AccountRepositoryImpl(mastodonApi, localCacheStore)
}
