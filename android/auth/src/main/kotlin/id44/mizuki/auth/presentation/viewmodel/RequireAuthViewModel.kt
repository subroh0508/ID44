package id44.mizuki.auth.presentation.viewmodel

import androidx.lifecycle.*
import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.libraries.api.TokenExpiredException
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.shared.Https
import kotlinx.coroutines.*

class RequireAuthViewModel(
    private val repository: AccessTokenRepository
) : ViewModel(), RequireAuthContract.ViewModel {
    private val _httpException: MutableLiveData<Throwable> by lazy(::MutableLiveData)
    private val _openAuthentication: MutableLiveData<Unit> by lazy(::MutableLiveData)

    private val _httpExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        when (throwable) {
            is TokenExpiredException -> repository.clearAccessToken(throwable.host, throwable.id)
            is Https.UnauthorizedError -> repository.clearAccessToken(throwable.host, throwable.id)
        }

        _httpException.postValue(throwable)

        if (repository.existAnyAuthenticatedAccounts()) return@CoroutineExceptionHandler

        _openAuthentication.postValue(Unit)
    }

    override fun launch(
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> Unit
    ) = viewModelScope.launch(_httpExceptionHandler, start, block)

    override fun <T> async(
        start: CoroutineStart,
        block: suspend CoroutineScope.() -> T
    ) = viewModelScope.async(_httpExceptionHandler, start, block)

    override fun <T> liveData(
        timeoutInMs: Long,
        block: suspend LiveDataScope<T>.() -> Unit
    ): LiveData<T> = liveData(_httpExceptionHandler, timeoutInMs, block)

    val httpException: LiveData<Throwable> get() = _httpException
    val openAuthentication: LiveData<Unit> get() = _openAuthentication
}
