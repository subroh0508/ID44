package id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken

import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.shared.valueobject.AccountId
import id44.mizuki.shared.valueobject.HostName

internal class SwitchAccessTokenUseCaseImpl(
    private val repository: AccessTokenRepository
) : SwitchAccessTokenUseCase {
    override fun execute(host: HostName, id: AccountId) =
        repository.saveNowAuthenticatedAccount(host, id)
}
