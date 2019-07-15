package id44.mizuki.auth.presenter

import id44.mizuki.auth.AuthenticationConstract
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase

class AuthenticationPresenter(
    private val view: AuthenticationConstract.View,
    private val requestAppCredentialUseCase: RequestAppCredentialUseCase,
    private val requestAccessTokenUseCase: RequestAccessTokenUseCase
) : AuthenticationConstract.Presenter {
    override suspend fun buildAuthorizeUrl(hostName: String): String
            = requestAppCredentialUseCase.execute(hostName)

    override suspend fun requestAccessToken(hostName: String, code: String): String
            = requestAccessTokenUseCase.execute(hostName, code)
}
