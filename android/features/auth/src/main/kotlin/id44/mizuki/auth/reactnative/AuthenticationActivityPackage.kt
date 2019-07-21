package id44.mizuki.auth.reactnative

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import id44.mizuki.auth.AuthenticationContract

class AuthenticationActivityPackage(
    private val viewModel: AuthenticationContract.Model,
    private val presenter: AuthenticationContract.Presenter
) : ReactPackage {
    override fun createViewManagers(
        reactContext: ReactApplicationContext
    ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

    override fun createNativeModules(
        reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = mutableListOf(
        AuthenticationActivityReactModule(reactContext, viewModel, presenter)
    )
}
