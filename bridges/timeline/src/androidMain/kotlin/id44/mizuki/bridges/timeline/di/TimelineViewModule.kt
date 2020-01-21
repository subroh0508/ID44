package id44.mizuki.bridges.timeline.di

import com.facebook.react.ReactNativeHost
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
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCase
import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.SubmitStatusUseCase
import id44.mizuki.libraries.timeline.domain.usecase.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.ToggleFavouriteUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.ToggleReblogUseCase
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCase

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
        internal fun provideStatusActions(
            view: TimelineView, accessTokenRepository: AccessTokenRepository,
            fetchStatusesUseCase: FetchStatusesUseCase,
            submitStatusUseCase: SubmitStatusUseCase,
            toggleFavouriteUseCase: ToggleFavouriteUseCase,
            toggleReblogUseCase: ToggleReblogUseCase
        ) = StatusActions(
            view,
            accessTokenRepository,
            fetchStatusesUseCase,
            submitStatusUseCase,
            toggleFavouriteUseCase,
            toggleReblogUseCase
        )

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideSubscribeActions(
            view: TimelineView, accessTokenRepository: AccessTokenRepository,
            subscribeUseCase: TimelineSubscribeUseCase,
            unsubscribeUseCase: TimelineUnsubscribeUseCase
        ) = SubscribeActions(view, accessTokenRepository, subscribeUseCase, unsubscribeUseCase)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideReactContextModuleProvider(
            ownAccountsActions: OwnAccountsActions,
            statusActions: StatusActions,
            subscribeActions: SubscribeActions
        ) = ReactContextModuleProvider { context ->
            TimelineReactModule(
                context,
                ownAccountsActions,
                statusActions,
                subscribeActions
            )
        }
    }
}
