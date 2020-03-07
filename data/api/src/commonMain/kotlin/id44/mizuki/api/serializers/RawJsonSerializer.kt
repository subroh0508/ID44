package id44.mizuki.api.serializers

import id44.mizuki.api.RawJson
import kotlinx.serialization.*
import kotlinx.serialization.internal.SerialClassDescImpl
import kotlinx.serialization.json.*

@Serializer(forClass = RawJson::class)
class RawJsonSerializer : KSerializer<RawJson> {
    override val descriptor = SerialClassDescImpl("RawJson")

    override fun deserialize(decoder: Decoder): RawJson {
        val input = decoder as? JsonInput ?: throw SerializationException("This class can be loaded only by Json")
        val jsonObj = input.decodeJson() as? JsonObject ?: throw SerializationException("Expected Json Object")

        return deserializeObject(jsonObj)
    }

    override fun serialize(encoder: Encoder, obj: RawJson) {
        val output = encoder as? JsonOutput ?: throw SerializationException("This class can be saved only by Json")

        output.encodeJson(serializeObject(obj.raw))
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
