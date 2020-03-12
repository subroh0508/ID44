package id44.mizuki.signin.viewmodel

import androidx.lifecycle.*
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.base.reactnative.ReactNativeViewModel
import id44.mizuki.domain.signin.usecase.RequestAccessTokenUseCase
import id44.mizuki.domain.signin.usecase.RequestAppCredentialUseCase
import id44.mizuki.shared.util.valueobject.Uri
import id44.mizuki.shared.util.valueobject.HostName
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class SignInViewModel(
    private val reactModule: SignInReactModule
) : ReactNativeViewModel(reactModule) {
    init { reactModule.scope = viewModelScope }

    val authorizeUri: LiveData<Uri> get() = reactModule.authorizeUri
}

class SignInReactModule(
    app: ReactApplicationContext,
    private val clientName: String,
    private val redirectUri: Uri,
    private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : ReactContextBaseJavaModule(app) {
    companion object {
        private const val NAME = "SignInNativeActions"
    }

    override fun getName() = NAME

    private lateinit var deferredCode: CompletableDeferred<String>

    lateinit var scope: CoroutineScope
    val authorizeUri: MutableLiveData<Uri> = MutableLiveData()

    @ReactMethod
    fun startOauth2Flow(host: String, promise: Promise) {
        scope.launch {
            val hostName = HostName(host)

            runCatching {
                requestAccessTokenUseCase.execute(hostName, redirectUri, fetchAuthorizeCode(hostName))
            }
                .onSuccess { promise.resolve(Unit) }
                .onFailure {
                    it.printStackTrace()
                    promise.reject(it)
                }
        }
    }
    @ReactMethod
    fun showErrorMessage(message: String) {

    }
    @ReactMethod
    fun openTimeline() {

    }

    private suspend fun fetchAuthorizeCode(host: HostName): String {
        authorizeUri.postValue(requestAppCredentialUseCase.execute(host, clientName, redirectUri))

        deferredCode = CompletableDeferred()
        return deferredCode.await()
    }
}

class SignInViewModelFactory(
    app: ReactApplicationContext,
    clientName: String,
    redirectUri: Uri,
    requestAppCredentialUseCase: RequestAppCredentialUseCase,
    requestAccessTokenUseCase: RequestAccessTokenUseCase
) : ViewModelProvider.NewInstanceFactory() {
    private val reactModule = SignInReactModule(
        app, clientName, redirectUri, requestAppCredentialUseCase, requestAccessTokenUseCase
    )

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        modelClass: Class<T>
    ): T = SignInViewModel(reactModule) as T
}
