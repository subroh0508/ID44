package id44.mizuki.signin.presentation.model

import android.content.Intent
import androidx.lifecycle.*
import id44.mizuki.bridges.signin.SignInViewModel
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.signin.AccessDeniedError
import id44.mizuki.signin.AuthorizeError
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

internal class SignInViewModelImpl(
        private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
        private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : ViewModel(), SignInViewModel {
    private lateinit var deferredCode: CompletableDeferred<String>

    val authorizeUri: LiveData<Uri> get() = _authorizeUri
    private val _authorizeUri: MutableLiveData<Uri> = MutableLiveData()

    val accessToken: LiveData<AccessToken> get() = _accessToken
    private val _accessToken: MutableLiveData<AccessToken> = MutableLiveData()

    val authorizeError: LiveData<Throwable> get() = _authorizeError
    private val _authorizeError: MutableLiveData<Throwable> = MutableLiveData()

    private val hostName: MutableLiveData<HostName> = MutableLiveData()

    private val authorizeErrorHandler =
        CoroutineExceptionHandler { _, throwable -> _authorizeError.postValue(throwable) }

    override fun onChangeHostName(host: HostName) = hostName.postValue(host)
    override fun startOauth2Flow(clientName: String, redirectUri: Uri) {
        viewModelScope.launch(authorizeErrorHandler) {
            val host = hostName.value ?: return@launch

            _authorizeUri.postValue(requestAppCredentialUseCase.execute(host, clientName, redirectUri))
            deferredCode = CompletableDeferred()

            _accessToken.postValue(requestAccessTokenUseCase.execute(host, redirectUri, deferredCode.await()))
        }
    }
    fun onNewIntent(intent: Intent?, redirectUri: Uri) {
        val authorizeCode = AuthorizeCode.fromIntent(intent, redirectUri)

        if (authorizeCode?.isSuccess == true) {
            deferredCode.complete(authorizeCode.code)
            return
        }

        val error = authorizeCode?.error
        deferredCode.completeExceptionally(
            when (error) {
                "access_denied" -> AccessDeniedError()
                null -> UnknownError(error)
                else -> AuthorizeError(error)
            }
        )
    }

    class Factory @Inject constructor(
            private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
            private val requestAccessTokenUseCase: RequestAccessTokenUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SignInViewModelImpl(requestAppCredentialUseCase, requestAccessTokenUseCase) as T
    }
}
