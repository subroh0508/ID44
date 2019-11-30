package id44.mizuki.auth.presentation.presenter


import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.libraries.api.TokenExpiredException
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.shared.Https
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import javax.inject.Inject

internal class RequireAuthPresenter @Inject constructor(
    private val view: RequireAuthContract.View,
    private val repository: AccessTokenRepository
) : RequireAuthContract.Presenter {
    override fun onHttpError(e: Throwable) {
        when (e) {
            is TokenExpiredException -> onUnauthorizedError(e.host, e.id)
            is Https.UnauthorizedError -> onUnauthorizedError(e.host, e.id)
        }

        view.showHttpErrorMessage(e)
    }

    private fun onUnauthorizedError(hostName: HostName, id: AccountId) {
        repository.clearAccessToken(hostName, id)

        if (!repository.existAnyAuthenticatedAccounts()) {
            view.openAuthentication()
            return
        }
    }
}
