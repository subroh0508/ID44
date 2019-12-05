package id44.mizuki.bridges.timeline

import android.app.Application
import com.facebook.react.ReactPackage
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import id44.mizuki.base.reactnative.BaseReactNativeHost
import javax.inject.Inject

internal class TimelineReactNativeHost @Inject constructor(
    app: Application,
    private val `package`: ReactPackage
) : BaseReactNativeHost(app) {
    override fun getPackages(): MutableList<ReactPackage> =
        super.getPackages().apply {
            addAll(listOf(RNGestureHandlerPackage(), ReanimatedPackage(), `package`))
        }
    override fun getBundleAssetName() = "index.android.bundle"
    override fun getJSMainModuleName() = "components/timeline/index"
}
