package id44.mizuki.libraries.api.json

import com.soywiz.klock.DateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PollJson(
    val id: String,
    @SerialName("expires_at")
    val expiresAt: DateTime? = null,
    val expired: Boolean,
    val multiple: Boolean,
    @SerialName("votes_count")
    val votesCount: Int,
    val options: List<PollOptionJson>,
    val voted: Boolean
)
