package id44.mizuki.authentication.reactnative

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.authentication.presentation.AuthenticationContract

class AuthenticationActivityReactModule(
    reactContext: ReactApplicationContext,
    private val viewModel: AuthenticationContract.Model,
    private val presenter: AuthenticationContract.Presenter
) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "AuthenticationContract"

    override fun getConstants() = mutableMapOf<String, Any>()

    @ReactMethod
    fun hostName(): String? = viewModel.hostName.value

    @ReactMethod
    fun onChangedHostName(hostName: String) = viewModel.bindHostName(hostName)

    @ReactMethod
    fun onClickAuthorize() = presenter.onClickAuthorize()
}
