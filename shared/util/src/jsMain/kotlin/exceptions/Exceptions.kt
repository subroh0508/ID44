package exceptions

import id44.mizuki.shared.util.exceptions.SerializableException

@JsName("parse")
fun <E: Enum<*>> parse(types: Array<E>, error: dynamic): SerializableException? =
    types.findReason(error)?.let { reason ->
        SerializableException(reason, (error.message as? String)?.replace("${reason.name}: ", ""))
    }

@JsName("key")
fun SerializableException?.key(module: String, defaultKey: String) =
    this?.let { "$module.exceptions.${reason.name.camelize()}" } ?: "$module.exceptions.$defaultKey"

private fun <E: Enum<*>> Array<E>.findReason(error: dynamic) =
    find { it.name == (error.message as? String)?.split(":")?.getOrNull(0) }

private fun String.camelize() = split("_").mapIndexed { index, s ->
    if (index == 0) {
        return@mapIndexed s.toLowerCase()
    }

    s.substring(0, 1) + s.substring(1).toLowerCase()
}.joinToString("")
