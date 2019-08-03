package id44.mizuki.libraries.api.json

import com.soywiz.klock.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FieldJson(
    val name: String,
    val value: String,
    @SerialName("verified_at")
    val verifiedAt: DateTime? = null
)
