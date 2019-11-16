package id44.mizuki.timeline.reactnative

import android.app.Application
import com.facebook.react.ReactPackage
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import id44.mizuki.base.reactnative.BaseReactNativeHost

class TimelineReactNativeHost(
    app: Application,
    private val timelinePackage: TimelineActivityPackage
) : BaseReactNativeHost(app) {
    override fun getPackages(): MutableList<ReactPackage> =
        super.getPackages().apply {
            addAll(listOf(RNGestureHandlerPackage(), ReanimatedPackage(), timelinePackage))
        }
    override fun getBundleAssetName() = "index.android.bundle"
    override fun getJSMainModuleName() = "components/timeline/index"
}