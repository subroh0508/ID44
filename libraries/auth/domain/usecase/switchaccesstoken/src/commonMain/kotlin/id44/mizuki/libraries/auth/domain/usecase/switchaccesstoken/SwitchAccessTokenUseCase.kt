package id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken

import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

interface SwitchAccessTokenUseCase {
    fun execute(host: HostName, id: AccountId)
}
