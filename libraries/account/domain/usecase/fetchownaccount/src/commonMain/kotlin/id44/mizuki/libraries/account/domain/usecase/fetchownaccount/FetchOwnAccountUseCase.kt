package id44.mizuki.libraries.account.domain.usecase.fetchownaccount

import id44.mizuki.libraries.account.domain.entity.Account
import id44.mizuki.libraries.shared.valueobject.HostName

interface FetchOwnAccountUseCase {
    suspend fun execute(hostName: HostName): Account
}
