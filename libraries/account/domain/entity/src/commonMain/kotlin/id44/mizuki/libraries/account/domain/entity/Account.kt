package id44.mizuki.libraries.account.domain.entity

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName

data class Account(
    val id: AccountId,
    val username: String,
    val displayName: String,
    val hostName: HostName
) {
    val screen: String = "$displayName@${hostName.value}"
}
