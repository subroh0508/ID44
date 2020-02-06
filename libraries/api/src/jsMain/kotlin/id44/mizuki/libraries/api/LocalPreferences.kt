package id44.mizuki.libraries.api

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

@JsModule("fs")
@JsNonModule
private external object fs {
    fun readFileSync(path: String): String
    fun writeFileSync(path: String, text: String)
}

private external fun delete(obj: dynamic): Boolean = definedExternally

actual class LocalPreferences(
    private val path: String,
    private val json: Json,
    private val filename: String
) {
    private val data: dynamic
    private val userDataPath get() = "$path/$filename.json"

    init {
        data = try {
            JSON.parse(fs.readFileSync(userDataPath))
        } catch (e: Throwable) {
            js("{}")
        }
    }

    actual fun putBoolean(key: String, value: Boolean) { put(key, value) }
    actual fun putString(key: String, value: String) { put(key, value) }
    actual fun putStringSet(key: String, value: Set<String>) { put(key, JSON.stringify(value.toArray())) }
    actual fun putInt(key: String, value: Int) { put(key, value) }
    actual fun putLong(key: String, value: Long) { put(key, value) }
    actual fun putFloat(key: String, value: Float) { put(key, value) }
    actual fun <T: Any> put(key: String, strategy: SerializationStrategy<T>, value: T) {
        put(key, json.stringify(strategy, value))
    }

    actual fun getBoolean(key: String, defValue: Boolean): Boolean = get(key) ?: defValue
    actual fun getString(key: String, defValue: String?): String? = get(key) ?: defValue
    actual fun getStringSet(key: String, defValue: Set<String>?): Set<String>? = (data[key] as? String)?.let {
        (JSON.parse(it) as Array<String>).toSet()
    }
    actual fun getInt(key: String, defValue: Int): Int = get(key) ?: defValue
    actual fun getLong(key: String, defValue: Long): Long = get(key) ?: defValue
    actual fun getFloat(key: String, defValue: Float): Float = get(key) ?: defValue
    actual fun <T: Any> get(key: String, strategy: DeserializationStrategy<T>): T? = (data[key] as? String)?.let {
        json.parse(strategy, it)
    }

    actual fun remove(key: String) { delete(data[key]) }

    private fun <E: Any> Set<E>.toArray(): Any = fold(js("[]")) { acc: dynamic, e: E ->
        acc.push(e)
        acc
    } as Any
    private fun <E: Any> toSet(array: dynamic): Set<E> = (array.length as Int).let { length ->
        if (length < 1) {
            return@let setOf<E>()
        }

        (0..length).map { array[it] as E }.toSet()
    }

    private fun put(key: String, value: Any) {
        data[key] = value
        fs.writeFileSync(userDataPath, JSON.stringify(data as Any))
    }
    private fun <T: Any> get(key: String): T? = data[key] as? T
}


