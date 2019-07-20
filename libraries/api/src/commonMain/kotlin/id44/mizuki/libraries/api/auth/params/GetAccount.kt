package id44.mizuki.libraries.api.auth.params

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

abstract class GetAccount {
    @Serializable
    data class Response(
        val id: String,
        val username: String,
        @SerialName("display_name")
        val displayName: String,
        val locked: Boolean
    )
}