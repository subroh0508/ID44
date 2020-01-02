package id44.mizuki.bridges.signin

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.reactnativesupport.ReactContextBaseModule
import id44.mizuki.libraries.reactnativesupport.ReactPromise
import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.coroutines.launch

internal actual class SignInNativeActions(
        reactContext: ReactApplicationContext,
        private val view: SignInView,
        private val viewModel: SignInViewModel
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "SignInNativeActions"

    @ReactMethod
    fun startOauth2Flow(host: String, promise: ReactPromise) {
        viewModel.scope.launch {
            runCatching { viewModel.startOauth2Flow(HostName(host)) }
                .onSuccess { promise.resolve(null) }
                .onFailure {
                    it.printStackTrace()
                    promise.reject(it)
                }
        }
    }
    @ReactMethod
    fun showToast(message: String) = view.showErrorMessage(message)
    @ReactMethod
    fun openTimeline() = view.openTimeline()
}
