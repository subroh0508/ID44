package id44.mizuki.shared.model.account

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
