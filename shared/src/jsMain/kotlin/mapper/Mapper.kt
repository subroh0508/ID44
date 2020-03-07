package mapper

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Mapper
import kotlinx.serialization.json.Json

@JsName("map")
fun <T: Any> map(serializer: KSerializer<T>, obj: T): dynamic = Mapper.mapNullable(
    serializer, obj
).toList().fold(js("{}")) { acc, (k, v) ->
    acc[k] = v
    acc
}

@JsName("unmap")
fun <T: Any> unmap(serializer: KSerializer<T>, dynamicObj: dynamic) = Mapper.unmapNullable(
    serializer,
    js("Object").keys(dynamicObj)
        .unsafeCast<Array<String>>()
        .map { key -> key to dynamicObj[key] }
        .toMap()
)

@JsName("stringify")
fun <T: Any> stringify(serializer: KSerializer<T>, obj: T) =
    Json.nonstrict.stringify(serializer, obj)

@JsName("parse")
fun <T: Any> parse(serializer: KSerializer<T>, json: String) =
    Json.nonstrict.parse(serializer, json)

