package id44.mizuki.commons.reactnativesupport

import android.app.Application
import android.view.View
import androidx.annotation.CallSuper
import com.dylanvann.fastimage.FastImageViewPackage
import com.facebook.react.ReactInstanceManager
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.shell.MainReactPackage
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import id44.mizuki.commons.BuildConfig

class SimpleReactNativeHost(
    app: Application,
    private val bundleName: String, private val jsModulePath: String,
    private val nativeModules: List<NativeModule> = listOf(),
    private val viewManagers: List<ViewManager<View, ReactShadowNode<*>>> = listOf(),
    private val packages: List<ReactPackage> = listOf()
) : ReactNativeHost(app) {
    private val featurePackage = object : ReactPackage {
        override fun createViewManagers(reactContext: ReactApplicationContext) = viewManagers
        override fun createNativeModules(reactContext: ReactApplicationContext) = nativeModules
    }

    override fun getPackages() = listOf(MainReactPackage(), featurePackage) + packages

    override fun getBundleAssetName() = bundleName
    override fun getJSMainModuleName() = jsModulePath

    override fun getUseDeveloperSupport() = BuildConfig.DEBUG
    override fun createReactInstanceManager(): ReactInstanceManager =
        super.createReactInstanceManager().apply {
            devSupportManager.setReloadOnJSChangeEnabled(BuildConfig.DEBUG)
        }
}
