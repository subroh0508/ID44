package id44.mizuki.timeline.di

import android.app.Application
import com.facebook.react.ReactInstanceManager
import com.facebook.react.common.LifecycleState
import com.facebook.react.shell.MainReactPackage
import com.oblador.vectoricons.VectorIconsPackage
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import dagger.Module
import dagger.Provides
import id44.mizuki.auth.di.AuthActivityModule
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.timeline.BuildConfig
import id44.mizuki.timeline.presentation.ui.TimelineActivity
import id44.mizuki.timeline.reactnative.TimelineActivityPackage

@Module
abstract class TimelineActivityModule : AuthActivityModule<TimelineActivity>() {
    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        fun provideTimelineActivityPackage(): TimelineActivityPackage = TimelineActivityPackage()

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactInstanceManager(
            app: Application,
            activity: TimelineActivity,
            `package`: TimelineActivityPackage
        ): ReactInstanceManager = ReactInstanceManager.builder()
            .setApplication(app)
            .setCurrentActivity(activity)
            .setBundleAssetName("index.android.bundle")
            .setJSMainModulePath("components/timeline/index")
            .addPackages(listOf(
                MainReactPackage(),
                VectorIconsPackage(),
                RNGestureHandlerPackage(),
                ReanimatedPackage(),
                `package`
            ))
            .setUseDeveloperSupport(BuildConfig.DEBUG)
            .setInitialLifecycleState(LifecycleState.RESUMED)
            .build().apply {
                devSupportManager.setReloadOnJSChangeEnabled(true)
            }
    }
}
