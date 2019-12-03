package id44.mizuki.timeline.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableArray
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.timeline.reactnative.OwnAccountsReactModule
import javax.inject.Inject

class OwnAccountsViewModel(
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
    private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
) : ViewModel(), OwnAccountsReactModule {
    val ownAccounts get() = fetchOwnAccountsUseCase.execute()
    val openAuthentication: LiveData<Unit> get() = _openAuthentication
    private val _openAuthentication: MutableLiveData<Unit> by lazy(::MutableLiveData)

    val restart: LiveData<Unit> get() = _restart
    private val _restart: MutableLiveData<Unit> by lazy(::MutableLiveData)

    class Factory @Inject constructor(
        private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
        private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            OwnAccountsViewModel(fetchOwnAccountsUseCase, switchAccessTokenUseCase) as T
    }

    override fun openAuthentication() = _openAuthentication.postValue(Unit)

    override fun fetchOwnAccounts(promise: Promise) = promise.resolve(
        Arguments.makeNativeArray(
            fetchOwnAccountsUseCase.execute().map { account ->
                Arguments.makeNativeMap(mapOf(
                    "id" to account.id.value,
                    "displayName" to account.displayName,
                    "screen" to account.screen,
                    "avatar" to account.avatar,
                    "hostName" to account.hostName.value
                ))
            }
        )
    )

    override fun switchAccount(host: String, id: String) {
        switchAccessTokenUseCase.execute(HostName(host), AccountId(id))
        _restart.postValue(Unit)
    }
}
