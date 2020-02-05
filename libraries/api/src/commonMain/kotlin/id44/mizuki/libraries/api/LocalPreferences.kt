package id44.mizuki.libraries.api

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy

expect class LocalPreferences {
    fun putBoolean(key: String, value: Boolean)
    fun putString(key: String, value: String)
    fun putStringSet(key: String, value: Set<String>)
    fun putInt(key: String, value: Int)
    fun putLong(key: String, value: Long)
    fun putFloat(key: String, value: Float)
    fun <T: Any> put(key: String, strategy: SerializationStrategy<T>, value: T)

    fun getBoolean(key: String, defValue: Boolean): Boolean
    fun getString(key: String, defValue: String?): String?
    fun getStringSet(key: String, defValue: Set<String>?): Set<String>?
    fun getInt(key: String, defValue: Int): Int
    fun getLong(key: String, defValue: Long): Long
    fun getFloat(key: String, defValue: Float): Float
    fun <T: Any> get(key: String, strategy: DeserializationStrategy<T>): T?

    fun remove(key: String)
}
