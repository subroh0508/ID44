package id44.mizuki.libraries.api.json.account

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldJson(
    val name: String,
    val value: String,
    @SerialName("verified_at")
    val verifiedAt: String? = null
)
