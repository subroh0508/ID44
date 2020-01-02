package id44.mizuki.bridges.timeline.di

import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.auth.di.RequireAuthViewModule
import id44.mizuki.bridges.shared.ReactContextModuleProvider
import id44.mizuki.bridges.timeline.*
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase

@Module
abstract class TimelineViewModule<in V: TimelineView> : RequireAuthViewModule<V>() {
    @Binds
    @ActivityScope
    abstract fun bindTimelineView(view: V): TimelineView

    @Binds
    @ActivityScope
    internal abstract fun bindReactNativeHost(host: TimelineReactNativeHost): ReactNativeHost

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideOwnAccountsActions(
            view: TimelineView, accessTokenRepository: AccessTokenRepository,
            fetchOwnAccountUseCase: FetchOwnAccountUseCase,
            fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
            switchAccessTokenUseCase: SwitchAccessTokenUseCase
        ) = OwnAccountsActions(view, accessTokenRepository, fetchOwnAccountUseCase, fetchOwnAccountsUseCase, switchAccessTokenUseCase)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideTimelineActions(
            view: TimelineView, accessTokenRepository: AccessTokenRepository,
            subscribeUseCase: TimelineSubscribeUseCase,
            unsubscribeUseCase: TimelineUnsubscribeUseCase
        ) = TimelineActions(view, accessTokenRepository, subscribeUseCase, unsubscribeUseCase)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideReactContextModuleProvider(
            ownAccountsActions: OwnAccountsActions,
            timelineActions: TimelineActions
        ) = ReactContextModuleProvider { context ->
            TimelineReactModule(
                context,
                ownAccountsActions,
                timelineActions
            )
        }
    }
}
