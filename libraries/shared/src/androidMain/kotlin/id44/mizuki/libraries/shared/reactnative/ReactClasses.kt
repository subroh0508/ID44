package id44.mizuki.libraries.shared.reactnative

import com.facebook.react.bridge.*

actual typealias ReactContextBaseModule = ReactContextBaseJavaModule
actual typealias ReactMap = ReadableMap
actual typealias ReactArray = ReadableArray
actual typealias ReactPromise = Promise

actual class ReactArguments {
    actual companion object {
        actual fun makeNativeMap(value: Map<String, Any?>): ReactMap = Arguments.makeNativeMap(value)
        actual fun makeNativeArray(value: List<Any?>): ReactArray = Arguments.makeNativeArray(value)
    }
}
