package id44.mizuki.commons.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import id44.mizuki.api.TokenExpiredException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import id44.mizuki.infra.auth.repository.AccessTokenRepository
import id44.mizuki.shared.util.Https
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

abstract class RequireAuthViewModel(
    private val repository: AccessTokenRepository
) : ViewModel() {
    val restartAuthorization: LiveData<Unit> get() = _restartAuthorization
    private val _restartAuthorization: MutableLiveData<Unit> = MutableLiveData()

    protected val existAnyAuthenticatedAccount get() = repository.existAnyAuthenticatedAccounts()

    protected fun clearAccessToken(hostName: HostName, accountId: AccountId) =
        repository.clearAccessToken(hostName, accountId)

    protected fun restartAuthorization() = _restartAuthorization.postValue(Unit)

    protected inline fun <R> runAuthCatching(block: () -> R): Result<R> = runCatching(block).apply {
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

    protected fun launch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(block = block)
    protected fun <T : Any> async(block: suspend CoroutineScope.() -> T) = viewModelScope.async(block = block)
}
