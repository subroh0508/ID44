package id44.mizuki.api.params

import id44.mizuki.api.JsonArrayData
import id44.mizuki.api.RawJsonArray
import kotlinx.serialization.Serializable

class GetTimelines {
    @Serializable
    data class Response(
        override val raw: RawJsonArray
    ) : JsonArrayData
}
