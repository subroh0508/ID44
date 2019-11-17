package id44.mizuki.libraries.account.domain.entity

import id44.mizuki.libraries.shared.valueobject.HostName

data class Account(
    val id: String,
    val username: String,
    val displayName: String,
    val hostName: HostName
)
