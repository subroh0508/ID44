package id44.mizuki.timeline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import id44.mizuki.commons.viewmodel.RequireAuthViewModel
import id44.mizuki.commons.viewmodel.runAuthCatching
import id44.mizuki.domain.timeline.usecase.*
import id44.mizuki.shared.model.account.Account
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import kotlinx.coroutines.launch
import kotlinx.serialization.Properties

class OwnAccountsViewModel(
    requireAuthViewModel: RequireAuthViewModel,
    private val fetchOwnAccountUseCase: FetchOwnAccountUseCase,
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
    private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
) : ViewModel(), RequireAuthViewModel by requireAuthViewModel {
    fun openAuthentication() = restartAuthorization()

    fun fetchOwnAccounts(promise: Promise) = promise.resolve(Arguments.makeNativeArray(
        fetchOwnAccountsUseCase.execute().map { Properties.store(Account.serializer(), it) }
    ))

    fun fetchOwnAccount(promise: Promise) = viewModelScope.launch {
        runAuthCatching { fetchOwnAccountUseCase.execute() }
            .onSuccess {
                promise.resolve(Arguments.makeNativeMap(Properties.store(Account.serializer(), it)))
            }
            .onFailure(promise::reject)
    }

    fun switchAccount(host: String, id: String) = switchAccessTokenUseCase.execute(HostName(host), AccountId(id))

    class Factory(
        private val requireAuthViewModel: RequireAuthViewModel,
        private val fetchOwnAccountUseCase: FetchOwnAccountUseCase,
        private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
        private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            OwnAccountsViewModel(
                requireAuthViewModel,
                fetchOwnAccountUseCase,
                fetchOwnAccountsUseCase,
                switchAccessTokenUseCase
            ) as T
    }
}
