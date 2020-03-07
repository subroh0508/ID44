package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthActions
import id44.mizuki.shared.model.account.Account
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.reactnativesupport.ReactArray
import id44.mizuki.libraries.reactnativesupport.ReactMap
import id44.mizuki.libraries.reactnativesupport.reactArray
import id44.mizuki.libraries.reactnativesupport.reactMap
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import kotlinx.coroutines.launch
import kotlinx.serialization.Mapper

internal class OwnAccountsActions(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val fetchOwnAccountUseCase: FetchOwnAccountUseCase,
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
    private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
) : RequireAuthActions(view, accessTokenRepository) {
    fun openAuthentication() = view.openAuthentication()

    fun fetchOwnAccounts(resolve: (ReactArray) -> Unit) = resolve(reactArray(
        fetchOwnAccountsUseCase.execute().map { Mapper.map(Account.serializer(), it) }
    ))

    fun fetchOwnAccount(resolve: (ReactMap) -> Unit, reject: (Throwable) -> Unit) = view.launch {
        runCatching { fetchOwnAccountUseCase.execute() }
            .onSuccess { resolve(reactMap(Mapper.map(Account.serializer(), it))) }
            .onHttpFailure(reject)
    }

    fun switchAccount(host: HostName, id: AccountId) {
        switchAccessTokenUseCase.execute(host, id)
        //view.restart()
    }
}
