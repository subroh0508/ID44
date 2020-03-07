package id44.mizuki.bridges.signin

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.reactnativesupport.ReactContextBaseModule
import id44.mizuki.libraries.reactnativesupport.ReactPromise
import id44.mizuki.shared.util.valueobject.HostName
import kotlinx.coroutines.launch

internal actual class SignInNativeActions(
        reactContext: ReactApplicationContext,
        private val view: SignInView
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "SignInNativeActions"

    @ReactMethod
    fun startOauth2Flow(host: String, promise: ReactPromise) = view.startOauth2Flow(host, promise::resolve, promise::reject)
    @ReactMethod
    fun showErrorMessage(message: String) = view.showErrorMessage(message)
    @ReactMethod
    fun openTimeline() = view.openTimeline()
}
