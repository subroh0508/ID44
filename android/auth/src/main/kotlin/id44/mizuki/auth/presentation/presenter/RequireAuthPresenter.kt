package id44.mizuki.auth.presentation.presenter


import id44.mizuki.auth.infra.AccessTokenRepository
import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.base.exception.Https
import id44.mizuki.libraries.api.TokenExpiredException
import javax.inject.Inject

internal class RequireAuthPresenter @Inject constructor(
    private val view: RequireAuthContract.View,
    private val repository: AccessTokenRepository
) : RequireAuthContract.Presenter {
    override fun onHttpError(e: Throwable) {
        when (e) {
            is TokenExpiredException -> onUnauthorizedError(e.hostName)
            is Https.UnauthorizedError -> onUnauthorizedError(e.hostName)
        }

        view.showHttpErrorMessage(e)
    }

    private fun onUnauthorizedError(hostName: String) {
        repository.clearAccessToken(hostName)

        if (repository.getAuthenticatedHostNames().isEmpty()) {
            view.openAuthentication()
            return
        }
    }
}
