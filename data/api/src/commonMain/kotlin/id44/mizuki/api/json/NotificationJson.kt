package id44.mizuki.api.json

import id44.mizuki.api.json.account.AccountJson
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationJson(
    val id: String,
    val type: String,
    @SerialName("created_at")
    val createdAt: String,
    val account: AccountJson,
    val status: StatusJson? = null
)
