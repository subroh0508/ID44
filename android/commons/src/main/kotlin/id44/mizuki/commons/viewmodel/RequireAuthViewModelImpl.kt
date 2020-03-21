package id44.mizuki.commons.viewmodel

import androidx.lifecycle.*
import id44.mizuki.api.TokenExpiredException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import id44.mizuki.infra.auth.repository.AccessTokenRepository
import id44.mizuki.shared.util.Https
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

internal class RequireAuthViewModelImpl(
    private val repository: AccessTokenRepository
) : ViewModel(), RequireAuthViewModel {
    override val restartAuthorization: LiveData<Unit> get() = _restartAuthorization
    private val _restartAuthorization: MutableLiveData<Unit> = MutableLiveData()

    override val existAnyAuthenticatedAccount get() = repository.existAnyAuthenticatedAccounts()

    override fun clearAccessToken(hostName: HostName, accountId: AccountId) =
        repository.clearAccessToken(hostName, accountId)

    override fun restartAuthorization() = _restartAuthorization.postValue(Unit)

    class Factory(
        private val repository: AccessTokenRepository
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = RequireAuthViewModelImpl(repository) as T
    }
}
