package id44.mizuki.bridges.timeline

import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

internal class OwnAccountsBridge(
    private val view: TimelineView,
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
    private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
) {
    fun openAuthentication() = view.openAuthentication()

    fun fetchOwnAccounts(): List<Map<String, Any>> = fetchOwnAccountsUseCase.execute().map { account ->
        mapOf(
            "id" to account.id.value,
            "displayName" to account.displayName,
            "screen" to account.screen,
            "avatar" to account.avatar,
            "hostName" to account.hostName.value
        )
    }

    fun switchAccount(host: HostName, id: AccountId) {
        switchAccessTokenUseCase.execute(host, id)
        //view.restart()
    }
}
