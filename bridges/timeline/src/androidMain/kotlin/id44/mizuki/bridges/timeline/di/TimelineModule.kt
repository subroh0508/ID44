package id44.mizuki.bridges.timeline.di

import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.di.FetchOwnAccountUseCaseModule
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.di.FetchOwnAccountsUseCaseModule
import id44.mizuki.libraries.account.infra.di.AccountRepositoryModule
import id44.mizuki.libraries.api.HttpsClientProvider
import id44.mizuki.libraries.api.di.MastodonApiModule
import id44.mizuki.libraries.api.di.MastodonStreamingApiModule
import id44.mizuki.libraries.api.params.GetAccountsVerifyCredential
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.di.SwitchAccessTokenUseCaseModule
import id44.mizuki.libraries.auth.infra.di.AccessTokenRepositoryModule
import id44.mizuki.libraries.timeline.domain.subscribe.di.TimelineSubscribeUseCaseModule
import id44.mizuki.libraries.timeline.domain.unsubscribe.di.TimelineUnsubscribeUseCaseModule
import id44.mizuki.libraries.timeline.infra.di.StreamingRepositoryModule

@Module(includes = [
    MastodonApiModule::class,
    MastodonStreamingApiModule::class,

    AccessTokenRepositoryModule::class,
    SwitchAccessTokenUseCaseModule::class,

    AccountRepositoryModule::class,
    FetchOwnAccountUseCaseModule::class,
    FetchOwnAccountsUseCaseModule::class,

    StreamingRepositoryModule::class,
    TimelineSubscribeUseCaseModule::class,
    TimelineUnsubscribeUseCaseModule::class
])
class TimelineModule {
    @Provides
    @ModuleScope
    fun provideHttpClientProvider() = HttpsClientProvider {
        setMapper(GetAccountsVerifyCredential.Response::class, GetAccountsVerifyCredential.Response.serializer())
    }
}
