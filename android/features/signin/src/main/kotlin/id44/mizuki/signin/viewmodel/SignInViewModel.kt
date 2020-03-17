package id44.mizuki.signin.viewmodel

import android.content.Intent
import android.util.Log
import androidx.lifecycle.*
import com.facebook.react.bridge.Promise
import id44.mizuki.domain.signin.usecase.RequestAccessTokenUseCase
import id44.mizuki.domain.signin.usecase.RequestAppCredentialUseCase
import id44.mizuki.shared.presentation.signin.exceptions.SignInError
import id44.mizuki.shared.util.exceptions.SerializableException
import id44.mizuki.shared.util.valueobject.Uri
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.signin.model.AuthorizeCode
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class SignInViewModel(
    private val clientName: String,
    private val redirectUri: Uri,
    private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : ViewModel() {
    val authorizeUri: LiveData<Uri> get() = _authorizeUri

    private lateinit var deferredCode: CompletableDeferred<String>

    private val _authorizeUri: MutableLiveData<Uri> = MutableLiveData()

    fun startOauth2Flow(host: String, promise: Promise) {
        viewModelScope.launch {
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
    fun showErrorMessage(message: String) {
        Log.d("showErrorMessage", message)
    }
    fun openTimeline() = Unit

    fun onNotFoundBrowser() = deferredCode.completeExceptionally(browserAppNotFoundError())
    fun onNewIntent(intent: Intent?) {
        val authorizeCode = AuthorizeCode.fromIntent(intent, redirectUri)

        if (authorizeCode?.isSuccess == true) {
            deferredCode.complete(authorizeCode.code)
            return
        }

        val error = authorizeCode?.error
        deferredCode.completeExceptionally(
            when (error) {
                "access_denied" -> accessDeniedError()
                null -> unknownError(error)
                else -> authorizeFailedError(error)
            }
        )
    }

    private suspend fun fetchAuthorizeCode(host: HostName): String {
        _authorizeUri.postValue(requestAppCredentialUseCase.execute(host, clientName, redirectUri))

        deferredCode = CompletableDeferred()
        return deferredCode.await()
    }

    private fun accessDeniedError() = SerializableException(SignInError.ACCESS_DENIED)
    private fun authorizeFailedError(message: String?) = SerializableException(SignInError.AUTHORIZE_FAILED, message)
    private fun browserAppNotFoundError() = SerializableException(SignInError.BROWSER_APP_NOT_FOUND)
    private fun unknownError(message: String?) = SerializableException(SignInError.UNKNOWN, message)

    class Factory(
        private val clientName: String,
        private val redirectUri: Uri,
        private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
        private val requestAccessTokenUseCase: RequestAccessTokenUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SignInViewModel(
                clientName,
                redirectUri,
                requestAppCredentialUseCase,
                requestAccessTokenUseCase
            ) as T
    }
}
