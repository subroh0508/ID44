package id44.mizuki.base.reactnative

import android.app.Application
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.shell.MainReactPackage
import com.oblador.vectoricons.VectorIconsPackage
import id44.mizuki.base.BuildConfig

abstract class BaseReactNativeHost(app: Application) : ReactNativeHost(app) {
    override fun getPackages(): MutableList<ReactPackage> = mutableListOf(
        MainReactPackage(), VectorIconsPackage()
    )
    override fun getUseDeveloperSupport() = BuildConfig.DEBUG
    override fun createReactInstanceManager(): ReactInstanceManager =
        super.createReactInstanceManager().apply {
            devSupportManager.setReloadOnJSChangeEnabled(BuildConfig.DEBUG)
        }
}