package id44.mizuki.commons.viewmodel

import androidx.lifecycle.LiveData
import id44.mizuki.api.TokenExpiredException
import id44.mizuki.shared.util.Https
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

interface RequireAuthViewModel {
    val restartAuthorization: LiveData<Unit>
    val existAnyAuthenticatedAccount: Boolean

    fun restartAuthorization()

    fun clearAccessToken(hostName: HostName, accountId: AccountId)
}

inline fun <R> RequireAuthViewModel.runAuthCatching(block: () -> R): Result<R> = runCatching(block).apply {
    exceptionOrNull()?.let {
        when (it) {
            is TokenExpiredException -> clearAccessToken(it.host, it.id)
            is Https.UnauthorizedError -> clearAccessToken(it.host, it.id)
        }
    }

    if (!existAnyAuthenticatedAccount) {
        restartAuthorization()
    }
}

