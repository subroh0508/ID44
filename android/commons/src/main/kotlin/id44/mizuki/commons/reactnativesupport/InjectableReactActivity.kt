package id44.mizuki.commons.reactnativesupport

import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance

abstract class InjectableReactActivity : ReactActivity(), KodeinAware {
    val host: ReactNativeHost by instance()
    val reactRootView: ReactRootView by instance()

    override fun createReactActivityDelegate() = object : ReactActivityDelegate(this, mainComponentName) {
        override fun getReactNativeHost(): ReactNativeHost = host
        override fun createRootView(): ReactRootView = reactRootView
    }
}
