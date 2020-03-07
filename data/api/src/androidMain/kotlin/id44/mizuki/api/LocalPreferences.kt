package id44.mizuki.api

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.json.Json

actual class LocalPreferences(
    private val sharedPreferences: SharedPreferences,
    private val json: Json
) {
    actual fun putBoolean(key: String, value: Boolean) = sharedPreferences.edit { putBoolean(key, value) }
    actual fun putString(key: String, value: String) = sharedPreferences.edit { putString(key, value) }
    actual fun putStringSet(key: String, value: Set<String>) = sharedPreferences.edit { putStringSet(key, value) }
    actual fun putInt(key: String, value: Int) = sharedPreferences.edit { putInt(key, value) }
    actual fun putLong(key: String, value: Long) = sharedPreferences.edit { putLong(key, value) }
    actual fun putFloat(key: String, value: Float) = sharedPreferences.edit { putFloat(key, value) }
    actual fun <T: Any> put(key: String, strategy: SerializationStrategy<T>, value: T) = sharedPreferences.edit {
        putString(key, json.stringify(strategy, value))
    }

    actual fun getBoolean(key: String, defValue: Boolean): Boolean = sharedPreferences.getBoolean(key, defValue)
    actual fun getString(key: String,  defValue: String?): String? = sharedPreferences.getString(key, defValue)
    actual fun getStringSet(key: String, defValue: Set<String>?): Set<String>? = sharedPreferences.getStringSet(key, defValue)
    actual fun getInt(key: String,  defValue: Int): Int = sharedPreferences.getInt(key, defValue)
    actual fun getLong(key: String,  defValue: Long): Long = sharedPreferences.getLong(key, defValue)
    actual fun getFloat(key: String,  defValue: Float): Float = sharedPreferences.getFloat(key, defValue)
    actual fun <T: Any> get(key: String, strategy: DeserializationStrategy<T>): T? = sharedPreferences.getString(key, null)?.let {
        json.parse(strategy, it)
    }

    actual fun remove(key: String) = sharedPreferences.edit { remove(key) }
}
