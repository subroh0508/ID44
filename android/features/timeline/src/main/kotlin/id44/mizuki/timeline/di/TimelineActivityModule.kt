package id44.mizuki.timeline.di

import android.app.Application
import androidx.lifecycle.ViewModelProvider
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.di.AuthActivityModule
import id44.mizuki.auth.presentation.viewmodel.RequireAuthViewModel
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.timeline.presentation.model.TimelineViewModel
import id44.mizuki.timeline.presentation.ui.TimelineActivity
import id44.mizuki.timeline.reactnative.TimelineActivityPackage
import id44.mizuki.timeline.reactnative.TimelineReactNativeHost
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase

@Module
abstract class TimelineActivityModule : AuthActivityModule<TimelineActivity>() {
    @Binds
    @ActivityScope
    abstract fun bindTimelineViewModelFactory(factory: TimelineViewModel.Factory): ViewModelProvider.NewInstanceFactory

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideTimelineActivityPackage(): TimelineActivityPackage = TimelineActivityPackage()

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactNativeHost(
            app: Application,
            timelinePackage: TimelineActivityPackage
        ): ReactNativeHost = TimelineReactNativeHost(app, timelinePackage)

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactRootView(activity: TimelineActivity): ReactRootView = RNGestureHandlerEnabledRootView(activity)

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideTimelineViewModel(
            activity: TimelineActivity,
            factory: ViewModelProvider.NewInstanceFactory
        ) = ViewModelProvider(activity, factory)[TimelineViewModel::class.java]
    }
}
