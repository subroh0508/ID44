package id44.mizuki.bridges.timeline

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import javax.inject.Inject

internal class TimelinePackage @Inject constructor(
    private val ownAccountsBridge: OwnAccountsBridge,
    private val timelineBridge: TimelineBridge
) : ReactPackage {
    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = mutableListOf(
        TimelineReactModule(reactContext, ownAccountsBridge, timelineBridge)
    )
}
