package id44.mizuki.timeline.reactnative

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import id44.mizuki.timeline.presentation.TimelineContract

class TimelineActivityPackage(
    // private val viewModel: TimelineContract.Model,
    //private val presenter: TimelineContract.Presenter
) : ReactPackage {
    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = mutableListOf(
        TimelineActivityReactModule(reactContext/*, viewmodel, presenter*/)
    )
}