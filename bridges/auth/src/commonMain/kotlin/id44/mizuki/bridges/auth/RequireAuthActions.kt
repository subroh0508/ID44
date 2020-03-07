package id44.mizuki.bridges.auth

import id44.mizuki.api.TokenExpiredException
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.shared.util.Https

abstract class RequireAuthActions(
    private val view: RequireAuthView,
    private val repository: AccessTokenRepository
) {
    fun <T> Result<T>.onHttpFailure(block: (Throwable) -> Unit) = onFailure {
        handleOnHttpException(it)
        block(it)
    }

    private fun handleOnHttpException(throwable: Throwable) {
        when (throwable) {
            is TokenExpiredException -> repository.clearAccessToken(throwable.host, throwable.id)
            is Https.UnauthorizedError -> repository.clearAccessToken(throwable.host, throwable.id)
        }

        if (!repository.existAnyAuthenticatedAccounts()) {
            view.openAuthentication()
        }
    }
}
