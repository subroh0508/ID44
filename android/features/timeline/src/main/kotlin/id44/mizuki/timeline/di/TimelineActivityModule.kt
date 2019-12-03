package id44.mizuki.timeline.di

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.ReactRootView
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.di.AuthActivityModule
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.timeline.presentation.model.TimelineViewModel
import id44.mizuki.timeline.presentation.ui.TimelineActivity
import id44.mizuki.timeline.reactnative.TimelineActivityPackage
import id44.mizuki.timeline.reactnative.TimelineReactNativeHost
import id44.mizuki.timeline.presentation.model.OwnAccountsViewModel
import javax.inject.Named

@Module
abstract class TimelineActivityModule : AuthActivityModule<TimelineActivity>() {
    @Binds
    @ActivityScope
    @Named("Timeline")
    abstract fun bindTimelineViewModelFactory(factory: TimelineViewModel.Factory): ViewModelProvider.Factory

    @Binds
    @ActivityScope
    @Named("OwnAccounts")
    abstract fun bindOwnAccountsViewModelFactory(factory: OwnAccountsViewModel.Factory): ViewModelProvider.Factory

    @Binds
    @ActivityScope
    abstract fun bindTimelineActivityPackage(`package`: TimelineActivityPackage): ReactPackage

    @Binds
    @ActivityScope
    abstract fun bindReactNativeHost(host: TimelineReactNativeHost): ReactNativeHost

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactRootView(activity: TimelineActivity): ReactRootView = RNGestureHandlerEnabledRootView(activity)

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideTimelineViewModel(
            activity: TimelineActivity,
            @Named("Timeline") factory: ViewModelProvider.Factory
        ) = ViewModelProvider(activity, factory)[TimelineViewModel::class.java]

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideOwnAccountsViewModel(
            activity: TimelineActivity,
            @Named("OwnAccounts") factory: ViewModelProvider.Factory
        ) = ViewModelProvider(activity, factory)[OwnAccountsViewModel::class.java]
    }
}
