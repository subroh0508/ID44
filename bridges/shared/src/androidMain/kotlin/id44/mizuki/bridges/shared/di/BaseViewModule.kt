package id44.mizuki.bridges.shared.di

import android.view.View
import com.facebook.react.ReactActivity
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.shared.ReactContextModuleProvider

@Module
abstract class BaseViewModule<in T: ReactNativeHost> {
    @Binds
    @ActivityScope
    internal abstract fun bindReactNativeHost(host: T): ReactNativeHost

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideReactPackage(
            moduleProvider: ReactContextModuleProvider
        ) = object : ReactPackage {
            override fun createViewManagers(
                reactContext: ReactApplicationContext
            ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

            override fun createNativeModules(
                reactContext: ReactApplicationContext
            ): MutableList<NativeModule> = mutableListOf(moduleProvider.invoke(reactContext))
        }
    }
}
