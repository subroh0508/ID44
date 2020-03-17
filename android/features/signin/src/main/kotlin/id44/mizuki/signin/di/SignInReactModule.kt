package id44.mizuki.signin.di

import com.facebook.react.bridge.BaseJavaModule
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.signin.viewmodel.SignInViewModel

class SignInReactModule(
    private val viewModel: SignInViewModel
) : BaseJavaModule() {
    companion object {
        private const val NAME = "SignInNativeActions"
    }

    override fun getName() = NAME

    @ReactMethod
    fun startOauth2Flow(host: String, promise: Promise) = viewModel.startOauth2Flow(host, promise)
    @ReactMethod
    fun showErrorMessage(message: String) = viewModel.showErrorMessage(message)
    @ReactMethod
    fun openTimeline() = viewModel.openTimeline()
}
