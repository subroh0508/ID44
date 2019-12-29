package id44.mizuki.libraries.shared.reactnative

expect abstract class ReactContextBaseModule
expect interface ReactMap
expect interface ReactArray

expect interface ReactPromise {
    fun resolve(value: Any?)
    fun reject(throwable: Throwable)
}

expect class ReactArguments {
    companion object {
        fun makeNativeMap(value: Map<String, Any?>): ReactMap
        fun makeNativeArray(value: List<Any?>): ReactArray
    }
}
