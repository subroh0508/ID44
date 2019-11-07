package id44.mizuki.auth.presentation.presenter


import id44.mizuki.auth.infra.AccessTokenStoreClientWrapper
import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.base.exception.Https
import javax.inject.Inject

internal class RequireAuthPresenter @Inject constructor(
    private val view: RequireAuthContract.View,
    private val accessTokenStore: AccessTokenStoreClientWrapper
) : RequireAuthContract.Presenter {
    override fun onHttpError(e: Throwable) {
        when (e) {
            is Https.UnauthorizedError -> onUnauthorizedError(e.hostName)
        }

        view.showHttpErrorMessage(e)
    }

    private fun onUnauthorizedError(hostName: String) {
        if (accessTokenStore.getAuthenticatedHostNames().isEmpty()) {
            view.openAuthentication()
            return
        }

        accessTokenStore.clearAccessToken(hostName)
    }
}