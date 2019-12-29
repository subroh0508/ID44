package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthBridge
import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.shared.reactnative.ReactArguments
import id44.mizuki.libraries.shared.reactnative.ReactPromise
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.coroutines.launch

internal class OwnAccountsBridge(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val fetchOwnAccountUseCase: FetchOwnAccountUseCase,
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
    private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
) : RequireAuthBridge(view, accessTokenRepository) {
    fun openAuthentication() = view.openAuthentication()

    fun fetchOwnAccounts(promise: ReactPromise) = promise.resolve(ReactArguments.makeNativeArray(
        fetchOwnAccountsUseCase.execute().map { it.toMap() }
    ))

    fun fetchOwnAccount(promise: ReactPromise) = view.launch {
        runCatching { fetchOwnAccountUseCase.execute() }
            .onSuccess { promise.resolve(it.toMap()) }
            .onHttpFailure(promise::reject)
    }

    fun switchAccount(host: HostName, id: AccountId) {
        switchAccessTokenUseCase.execute(host, id)
        //view.restart()
    }

    private fun Account.toMap() = ReactArguments.makeNativeMap(mapOf(
        "id" to id.value,
        "displayName" to displayName,
        "screen" to screen,
        "avatar" to avatar,
        "hostName" to hostName.value
    ))
}
