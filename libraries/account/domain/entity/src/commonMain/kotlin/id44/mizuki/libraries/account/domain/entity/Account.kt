package id44.mizuki.libraries.account.domain.entity

import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.serialization.Serializable
import kotlin.js.JsName

@Serializable
@JsName("Account")
data class Account(
    private val id: String,
    val username: String,
    val displayName: String,
    val hostName: String,
    val avatar: String
) {
    val screen: String = "$username@${hostName}"

    @JsName("_id")
    fun id() = AccountId(id)
    @JsName("_hostName")
    fun hostName() = HostName(hostName)
}
