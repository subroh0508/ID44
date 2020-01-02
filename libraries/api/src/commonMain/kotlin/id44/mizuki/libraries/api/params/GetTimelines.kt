package id44.mizuki.libraries.api.params

import id44.mizuki.libraries.api.JsonArrayData
import id44.mizuki.libraries.api.RawJsonArray
import kotlinx.serialization.Serializable

class GetTimelines {
    @Serializable
    data class Response(
        override val raw: RawJsonArray
    ) : JsonArrayData
}
