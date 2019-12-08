package id44.mizuki.timeline.di

import com.facebook.react.ReactRootView
import com.swmansion.gesturehandler.react.RNGestureHandlerEnabledRootView
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.timeline.di.TimelineViewModule
import id44.mizuki.timeline.TimelineActivity

@Module
abstract class TimelineActivityModule : TimelineViewModule<TimelineActivity>() {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactRootView(activity: TimelineActivity): ReactRootView = RNGestureHandlerEnabledRootView(activity)
    }
}
