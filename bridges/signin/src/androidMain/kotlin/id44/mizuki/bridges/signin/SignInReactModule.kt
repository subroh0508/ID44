package id44.mizuki.bridges.signin

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.shared.reactnative.ReactContextBaseModule
import id44.mizuki.libraries.shared.reactnative.ReactPromise
import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

internal actual class SignInReactModule(
        reactContext: ReactApplicationContext,
        private val viewModel: SignInViewModel
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "SignInModule"

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
    fun showToast(message: String) = viewModel.showToast(message)
    @ReactMethod
    fun openTimeline() = viewModel.openTimeline()
}
