package id44.mizuki.timeline.reactnative

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import id44.mizuki.timeline.presentation.model.OwnAccountsViewModel
import javax.inject.Inject

class TimelineActivityPackage @Inject constructor(
    private val ownAccountsViewModel: OwnAccountsViewModel
) : ReactPackage {
    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = mutableListOf(
        TimelineActivityReactModule(reactContext, ownAccountsViewModel)
    )
}
