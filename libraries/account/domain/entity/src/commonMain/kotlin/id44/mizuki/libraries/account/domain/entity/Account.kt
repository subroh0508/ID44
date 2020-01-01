package id44.mizuki.libraries.account.domain.entity

import kotlinx.serialization.Serializable

@Serializable
data class Account(
    val id: String,
    val username: String,
    val displayName: String,
    val hostName: String,
    val avatar: String
) {
    val screen: String = "$username@${hostName}"
}
