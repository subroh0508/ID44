package id44.mizuki

import android.app.Application
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader

class MainApplication : Application(), ReactApplication {
    private val reactNativeHost: ReactNativeHost = object : ReactNativeHost(this) {
        override fun getPackages() = listOf(MainReactPackage())
        override fun getUseDeveloperSupport() = BuildConfig.DEBUG
        override fun getJSMainModuleName() = "index"
    }

    override fun getReactNativeHost() = reactNativeHost

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)
    }
}
