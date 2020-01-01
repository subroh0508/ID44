package mapper

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Mapper

@JsName("unmap")
fun <T: Any> unmap(serializer: KSerializer<T>, dynamicObj: dynamic) = Mapper.unmap(
    serializer,
    js("Object").keys(dynamicObj)
        .unsafeCast<Array<String>>()
        .map { key -> key to dynamicObj[key] }
        .toMap()
)
