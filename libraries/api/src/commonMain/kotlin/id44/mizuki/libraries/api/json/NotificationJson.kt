package id44.mizuki.libraries.api.json

import com.soywiz.klock.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class NotificationJson(
    val id: String,
    val type: String,
    @SerialName("created_at")
    val createdAt: DateTime,
    val account: AccountJson,
    val status: StatusJson? = null
)
