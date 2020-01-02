package id44.mizuki.bridges.shared

import com.facebook.react.bridge.ReactApplicationContext
import id44.mizuki.libraries.reactnativesupport.ReactContextBaseModule

class ReactContextModuleProvider(
    private val provider: (ReactApplicationContext) -> ReactContextBaseModule
) {
    fun provide(reactApplicationContext: ReactApplicationContext) = provider(reactApplicationContext)
}
