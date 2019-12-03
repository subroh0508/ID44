package id44.mizuki.timeline.presentation.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableArray
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.timeline.reactnative.OwnAccountsReactModule
import javax.inject.Inject

class OwnAccountsViewModel(
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase
) : ViewModel(), OwnAccountsReactModule {
    val ownAccounts get() = fetchOwnAccountsUseCase.execute()

    class Factory @Inject constructor(
        private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            OwnAccountsViewModel(fetchOwnAccountsUseCase) as T
    }

    override fun fetchOwnAccounts(promise: Promise) = promise.resolve(
        Arguments.makeNativeArray(
            fetchOwnAccountsUseCase.execute().map { account ->
                Arguments.makeNativeMap(mapOf(
                    "id" to account.id.value,
                    "username" to account.username,
                    "screen" to account.screen
                ))
            }
        )
    )
}
