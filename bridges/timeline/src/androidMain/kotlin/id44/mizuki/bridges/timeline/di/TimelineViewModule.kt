package id44.mizuki.bridges.timeline.di

import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.auth.di.RequireAuthViewModule
import id44.mizuki.bridges.timeline.*
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase

@Module
abstract class TimelineViewModule<in V: TimelineView> : RequireAuthViewModule<V>() {
    @Binds
    @ActivityScope
    abstract fun bindTimelineView(view: V): TimelineView

    @Binds
    @ActivityScope
    internal abstract fun bindTimelinePackage(`package`: TimelinePackage): ReactPackage

    @Binds
    @ActivityScope
    internal abstract fun bindReactNativeHost(host: TimelineReactNativeHost): ReactNativeHost

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideOwnAccountsBridge(
            view: TimelineView,
            fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
            switchAccessTokenUseCase: SwitchAccessTokenUseCase
        ) = OwnAccountsBridge(view, fetchOwnAccountsUseCase, switchAccessTokenUseCase)

        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideTimelineBridge(
            view: TimelineView,
            subscribeUseCase: TimelineSubscribeUseCase,
            unsubscribeUseCase: TimelineUnsubscribeUseCase
        ) = TimelineBridge(view, subscribeUseCase, unsubscribeUseCase)
    }
}
