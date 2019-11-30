package id44.mizuki.authentication.presentation.presenter

import id44.mizuki.authentication.AccessDeniedError
import id44.mizuki.authentication.AuthorizeError
import id44.mizuki.authentication.BrowserAppNotFoundError
import id44.mizuki.authentication.UnknownError
import id44.mizuki.authentication.presentation.AuthenticationContract
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

internal class AuthenticationPresenter @Inject constructor(
    private val view: AuthenticationContract.View,
    private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : AuthenticationContract.Presenter {
    private lateinit var deferred: CompletableDeferred<String>

    override fun onClickAuthorize() = view.startOauth2Flow()

    override fun onNewIntent(code: String?, error: String?) {
        if (code.isNullOrBlank()) {
            deferred.completeExceptionally(
                when (error) {
                    "access_denied" -> AccessDeniedError()
                    null -> UnknownError(error)
                    else -> AuthorizeError(error)
                }
            )
            return
        }

        deferred.complete(code)
    }


    override suspend fun fetchAuthorizeCode(hostName: HostName, clientName: String, redirectUri: Uri): String {
        view.openAuthorizePage(requestAppCredentialUseCase.execute(hostName, clientName, redirectUri))

        deferred = CompletableDeferred()
        return deferred.await()
    }

    override suspend fun requestAccessToken(hostName: HostName, redirectUri: Uri, code: String) =
        requestAccessTokenUseCase.execute(hostName, redirectUri, code)

    override fun onRequestedAccessToken(token: AccessToken) = view.bindAccessToken(token)

    override fun notifyBrowserNotFound() {
        deferred.completeExceptionally(BrowserAppNotFoundError())
    }
}
