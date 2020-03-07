package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName

interface SwitchAccessTokenUseCase {
    fun execute(host: HostName, id: AccountId)
}
