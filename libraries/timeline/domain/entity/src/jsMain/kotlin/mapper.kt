import id44.mizuki.libraries.timeline.domain.entity.Status
import kotlinx.serialization.Mapper

@JsName("unmapStatus")
fun unmapStatus(dynamicObj: dynamic) = Mapper.unmap(
    Status.serializer(),
    js("Object").keys(dynamicObj).unsafeCast<Array<String>>().map { key ->
        key to dynamicObj[key]
    }.toMap()
)
