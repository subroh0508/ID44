package id44.mizuki.bridges.signin

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.shared.reactnative.ReactContextBaseModule
import id44.mizuki.libraries.shared.valueobject.HostName

internal actual class SignInReactModule(
        reactContext: ReactApplicationContext,
        private val view: SignInView,
        private val viewModel: SignInViewModel
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "SignInModule"

    @ReactMethod
    fun onChangedHostName(hostName: String) = viewModel.onChangeHostName(HostName(hostName))
    @ReactMethod
    fun onClickAuthorize() = viewModel.startOauth2Flow(view.clientName, view.redirectUri)
}
