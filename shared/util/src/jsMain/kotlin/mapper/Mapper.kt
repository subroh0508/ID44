package mapper

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Properties
import kotlinx.serialization.json.Json

@JsName("map")
fun <T: Any> map(serializer: KSerializer<T>, obj: T): dynamic = Properties.storeNullable(
    serializer, obj
).toList().fold(js("{}")) { acc, (k, v) ->
    acc[k] = v
    acc
}

@JsName("unmap")
fun <T: Any> unmap(serializer: KSerializer<T>, dynamicObj: dynamic) = Properties.loadNullable(
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

