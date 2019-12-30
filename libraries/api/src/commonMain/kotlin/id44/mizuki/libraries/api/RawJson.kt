package id44.mizuki.libraries.api

import id44.mizuki.libraries.api.serializers.RawJsonSerializer
import kotlinx.serialization.Serializable
import kotlin.reflect.KProperty

@Serializable(with = RawJsonSerializer::class)
data class RawJson(val raw: Map<String, Any?>) {
    operator fun get(key: String) = raw[key.snakize()]

    @Suppress("UNCHECKED_CAST")
    operator fun <T: Any?> getValue(thisRef: Any?, property: KProperty<*>): T = raw[property.name.snakize()] as T

    private fun camelize(map: Map<String, Any?>): Map<String, Any?> =
        map.map { (k, v) ->
            @Suppress("UNCHECKED_CAST")
            k.camelize() to if (v is Map<*, *>) camelize(v as Map<String, Any?>) else v
        }.toMap()

    private fun String.camelize() = split("_").mapIndexed { index, s ->
        if (index == 0) {
            return@mapIndexed s
        }

        s.capitalize()
    }.joinToString("")

    private fun String.snakize() = replace("([A-Z]+)([A-Z][a-z])".toRegex(), "$1_$2")
        .replace("([a-z])([A-Z])".toRegex(), "$1_$2").toLowerCase()

    class Delegate<out T: JsonData>(
        private val initializer: (RawJson) -> T
    ) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
            val ref = thisRef as? JsonData ?: throw IllegalStateException()
            val value = ref[property.name] as? RawJson ?: throw IllegalStateException()

            return initializer(value)
        }
    }

    class NullableDelegate<out T: JsonData>(
        private val initializer: (RawJson) -> T
    ) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): T? {
            val ref = thisRef as? JsonData ?: throw IllegalStateException()
            val value = ref[property.name] as? RawJson

            return value?.let(initializer)
        }
    }

    class ListDelegate<out T: JsonData>(
        private val initializer: (RawJson) -> T
    ) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T> {
            val ref = thisRef as? JsonData ?: throw IllegalStateException()
            @Suppress("UNCHECKED_CAST")
            val list = ref[property.name] as? List<RawJson> ?: listOf()

            return list.map(initializer)
        }
    }

    class NullableListDelegate<out T: JsonData>(
        private val initializer: (RawJson) -> T
    ) {
        operator fun getValue(thisRef: Any?, property: KProperty<*>): List<T>? {
            val ref = thisRef as? JsonData ?: throw IllegalStateException()
            @Suppress("UNCHECKED_CAST")
            val list = ref[property.name] as? List<RawJson>

            return list?.map(initializer)
        }
    }
}
