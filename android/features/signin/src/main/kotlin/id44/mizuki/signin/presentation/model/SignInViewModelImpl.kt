package id44.mizuki.signin.presentation.model

import android.content.Intent
import androidx.lifecycle.*
import id44.mizuki.bridges.signin.SignInViewModel
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.signin.SignInError
import id44.mizuki.signin.SignInException
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

internal class SignInViewModelImpl(
        private val clientName: String,
        private val redirectUri: Uri,
        private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
        private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : ViewModel(), SignInViewModel {
    override val scope get() = viewModelScope

    override suspend fun startOauth2Flow(host: HostName) {
        _authorizeUri.postValue(requestAppCredentialUseCase.execute(host, clientName, redirectUri))

        deferredCode = CompletableDeferred()
        val code = deferredCode.await()

        requestAccessTokenUseCase.execute(host, redirectUri, code)
    }
    override fun openTimeline() = _openTimeline.postValue(Unit)

    private lateinit var deferredCode: CompletableDeferred<String>

    val authorizeUri: LiveData<Uri> get() = _authorizeUri
    private val _authorizeUri: MutableLiveData<Uri> = MutableLiveData()

    val openTimeline: LiveData<Unit> get() = _openTimeline
    private val _openTimeline: MutableLiveData<Unit> = MutableLiveData()

    fun onNotFoundBrowser() = deferredCode.completeExceptionally(SignInException(SignInError.BROWSER_APP_NOT_FOUND))
    fun onNewIntent(intent: Intent?) {
        val authorizeCode = AuthorizeCode.fromIntent(intent, redirectUri)

        if (authorizeCode?.isSuccess == true) {
            deferredCode.complete(authorizeCode.code)
            return
        }

        val error = authorizeCode?.error
        deferredCode.completeExceptionally(
            when (error) {
                "access_denied" -> SignInException(SignInError.ACCESS_DENIED)
                null -> SignInException(SignInError.UNKNOWN)
                else -> SignInException(SignInError.AUTHORIZE_FAILED, error)
            }
        )
    }

    class Factory @Inject constructor(
            private val clientName: String,
            private val redirectUri: Uri,
            private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
            private val requestAccessTokenUseCase: RequestAccessTokenUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            SignInViewModelImpl(clientName, redirectUri, requestAppCredentialUseCase, requestAccessTokenUseCase) as T
    }
}
