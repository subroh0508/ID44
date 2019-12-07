package id44.mizuki.timeline.di

import com.facebook.react.ReactRootView
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.di.AuthActivityModule
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.timeline.TimelineView
import id44.mizuki.bridges.timeline.di.TimelinePackageModule
import id44.mizuki.timeline.TimelineActivity

@Module(includes = [TimelinePackageModule::class])
abstract class TimelineActivityModule : AuthActivityModule<TimelineActivity>() {
    @Binds
    @ActivityScope
    abstract fun bindTimelineView(activity: TimelineActivity): TimelineView

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactRootView(activity: TimelineActivity): ReactRootView = RNGestureHandlerEnabledRootView(activity)
    }
}
