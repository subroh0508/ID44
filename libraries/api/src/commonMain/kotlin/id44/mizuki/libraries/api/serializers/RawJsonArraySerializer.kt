package id44.mizuki.libraries.api.serializers

import id44.mizuki.libraries.api.RawJson
import id44.mizuki.libraries.api.RawJsonArray
import kotlinx.serialization.Decoder
import kotlinx.serialization.Encoder
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializer
import kotlinx.serialization.internal.ArrayListSerializer
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonLiteral
import kotlinx.serialization.json.JsonNull
import kotlinx.serialization.json.JsonObject

@Serializer(forClass = RawJsonArray::class)
class RawJsonArraySerializer : KSerializer<RawJsonArray> {
    override val descriptor = SerialClassDescImpl("RawJsonArray")

    override fun deserialize(decoder: Decoder): RawJsonArray {
        val raw: List<RawJson> = decoder.decodeSerializableValue(
            ArrayListSerializer(RawJsonSerializer())
        )

        return RawJsonArray(raw)
    }

    override fun serialize(encoder: Encoder, obj: RawJsonArray) {
        encoder.encodeSerializableValue(
            ArrayListSerializer(RawJsonSerializer()),
            obj.raw
        )
    }

    private fun deserializeObject(jsonObj: JsonObject): RawJson = RawJson(jsonObj.map { (key, element) ->
        key to when (element) {
            is JsonObject -> deserializeObject(element)
            is JsonArray -> deserializeArray(element)
            is JsonLiteral -> deserializeLiteral(element)
            else -> null
        }
    }.toMap())
    private fun deserializeArray(jsonArray: JsonArray): List<Any?> = jsonArray.map { element ->
        when (element) {
            is JsonObject -> deserializeObject(element)
            is JsonArray -> deserializeArray(element)
            is JsonLiteral -> deserializeLiteral(element)
            else -> null
        }
    }
    private fun deserializeLiteral(jsonLiteral: JsonLiteral): Any = when {
        jsonLiteral.isString -> jsonLiteral.content
        !jsonLiteral.isString && (jsonLiteral.content == "true" || jsonLiteral.content == "false") -> jsonLiteral.boolean
        jsonLiteral.content.contains(".") -> jsonLiteral.double
        else -> jsonLiteral.long
    }

    private fun serializeObject(obj: Map<String, Any?>): JsonObject = JsonObject(
        obj.map { (key, element) ->
            key to when (element) {
                is RawJson -> serializeObject(element.raw)
                is List<*> -> serializeArray(element as List<Any?>)
                is Boolean -> JsonLiteral(element)
                is Number -> JsonLiteral(element)
                is String -> JsonLiteral(element)
                else -> JsonNull
            }
        }.toMap()
    )
    private fun serializeArray(array: List<Any?>): JsonArray = JsonArray(
        array.map { element ->
            when (element) {
                is RawJson -> serializeObject(element.raw)
                is List<*> -> serializeArray(element as List<Any?>)
                is Boolean -> JsonLiteral(element)
                is Number -> JsonLiteral(element)
                is String -> JsonLiteral(element)
                else -> JsonNull
            }
        }
    )
}
