package id44.mizuki.auth.presenter

import id44.mizuki.auth.*
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
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


    override suspend fun fetchAuthorizeCode(hostName: String, clientName: String, redirectUri: String): String {
        view.openAuthorizePage(requestAppCredentialUseCase.execute(hostName, clientName, redirectUri))

        deferred = CompletableDeferred()
        return deferred.await()
    }

    override suspend fun requestAccessToken(hostName: String, redirectUri: String, code: String): String
            = requestAccessTokenUseCase.execute(hostName, redirectUri, code)

    override fun onRequestedAccessToken(accessToken: String)
            = view.bindAccessToken(accessToken)

    override fun notifyBrowserNotFound() {
        deferred.completeExceptionally(BrowserAppNotFoundError())
    }
}
