package id44.mizuki.libraries.reactnativesupport

actual abstract class ReactContextBaseModule
actual interface ReactMap : Map<String, Any?>
actual interface ReactArray : List<Any?>

actual interface ReactPromise {
    actual fun resolve(value: Any?)
    actual fun reject(throwable: Throwable)
}

actual class ReactArguments {
    actual companion object {
        actual fun makeNativeMap(value: Map<String, Any?>): ReactMap = value as ReactMap
        actual fun makeNativeArray(value: List<Any?>): ReactArray = value as ReactArray
    }
}
