package id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

interface SwitchAccessTokenUseCase {
    fun execute(host: HostName, id: AccountId)
}
