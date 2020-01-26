package id44.mizuki.bridges.signin

import android.app.Application
import com.facebook.react.ReactPackage
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import id44.mizuki.base.reactnative.BaseReactNativeHost

internal class SignInReactNativeHost(
    app: Application,
    private val `package`: ReactPackage
) : BaseReactNativeHost(app) {
    override fun getPackages(): MutableList<ReactPackage> =
        super.getPackages().apply {
            addAll(listOf(RNGestureHandlerPackage(), ReanimatedPackage(), `package`))
        }
    override fun getBundleAssetName() = "index.android.bundle"
    override fun getJSMainModuleName() = "frontend/components/signin/index"
}
