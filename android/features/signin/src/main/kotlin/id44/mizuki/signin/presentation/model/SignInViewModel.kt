package id44.mizuki.signin.presentation.model

import android.content.Intent
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id44.mizuki.bridges.signin.exceptions.SignInError
import id44.mizuki.domain.signin.usecase.RequestAccessTokenUseCase
import id44.mizuki.domain.signin.usecase.RequestAppCredentialUseCase
import id44.mizuki.shared.util.exceptions.SerializableException
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri
import kotlinx.coroutines.CompletableDeferred

internal class SignInViewModel(
        private val clientName: String,
        private val redirectUri: Uri,
        private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
        private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : ViewModel() {
    suspend fun startOauth2Flow(host: HostName) {
        _authorizeUri.postValue(requestAppCredentialUseCase.execute(host, clientName, redirectUri))

        deferredCode = CompletableDeferred()
        val code = deferredCode.await()

        requestAccessTokenUseCase.execute(host, redirectUri, code)
    }

    private lateinit var deferredCode: CompletableDeferred<String>

    val authorizeUri: LiveData<Uri> get() = _authorizeUri
    private val _authorizeUri: MutableLiveData<Uri> = MutableLiveData()

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

    class Factory(
            private val clientName: String,
            private val redirectUri: Uri,
            private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
            private val requestAccessTokenUseCase: RequestAccessTokenUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SignInViewModel(clientName, redirectUri, requestAppCredentialUseCase, requestAccessTokenUseCase) as T
    }

    private fun accessDeniedError() = SerializableException(SignInError.ACCESS_DENIED)
    private fun authorizeFailedError(message: String?) = SerializableException(SignInError.AUTHORIZE_FAILED, message)
    private fun browserAppNotFoundError() = SerializableException(SignInError.BROWSER_APP_NOT_FOUND)
    private fun unknownError(message: String?) = SerializableException(SignInError.UNKNOWN, message)
}
