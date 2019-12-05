package id44.mizuki.bridges.timeline

import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

internal class TimelineBridgeImpl(
    private val view: TimelineView,
    private val fetchOwnAccountsUseCase: FetchOwnAccountsUseCase,
    private val switchAccessTokenUseCase: SwitchAccessTokenUseCase
) : TimelineBridge {
    override fun openAuthentication() = view.openAuthentication()

    override fun fetchOwnAccounts(): List<Map<String, Any>> = fetchOwnAccountsUseCase.execute().map { account ->
        mapOf(
            "id" to account.id.value,
            "displayName" to account.displayName,
            "screen" to account.screen,
            "avatar" to account.avatar,
            "hostName" to account.hostName.value
        )
    }

    override fun switchAccount(host: String, id: String) {
        switchAccessTokenUseCase.execute(HostName(host), AccountId(id))
        view.restart()
    }
}
