package id44.mizuki.api

import id44.mizuki.api.serializers.RawJsonArraySerializer
import kotlinx.serialization.Serializable

@Serializable(with = RawJsonArraySerializer::class)
data class RawJsonArray(val raw: List<RawJson>) {
    operator fun get(index: Int) = raw[index]

    fun <V> map(block: (RawJson) -> V) = raw.map(block)
}
