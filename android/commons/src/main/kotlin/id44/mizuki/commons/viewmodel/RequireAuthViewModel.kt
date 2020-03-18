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

abstract class RequireAuthViewModel(
    private val repository: AccessTokenRepository
) : ViewModel() {
    val restartAuthorization: LiveData<Unit> get() = _restartAuthorization
    private val _restartAuthorization: MutableLiveData<Unit> = MutableLiveData()

    protected fun launch(block: suspend CoroutineScope.() -> Unit) = viewModelScope.launch(block = block)
    protected fun <T: Any> async(block: suspend CoroutineScope.() -> T) = viewModelScope.async(block = block)

    protected fun <R> runCatching(block: () -> R): Result<R> = kotlin.runCatching(block).apply {
        exceptionOrNull()?.let {
            when (it) {
                is TokenExpiredException -> repository.clearAccessToken(it.host, it.id)
                is Https.UnauthorizedError -> repository.clearAccessToken(it.host, it.id)
            }
        }

        if (!repository.existAnyAuthenticatedAccounts()) {
            _restartAuthorization.postValue(Unit)
        }
    }
}
