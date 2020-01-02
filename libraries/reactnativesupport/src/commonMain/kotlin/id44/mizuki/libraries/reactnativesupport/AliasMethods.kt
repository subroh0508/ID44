package id44.mizuki.libraries.reactnativesupport

fun reactMap(value: Map<String, Any?>) = ReactArguments.makeNativeMap(value)
fun reactArray(value: List<Any?>) = ReactArguments.makeNativeArray(value)
