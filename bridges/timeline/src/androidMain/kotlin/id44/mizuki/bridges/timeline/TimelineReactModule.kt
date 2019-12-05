package id44.mizuki.bridges.timeline

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.shared.reactnative.ReactContextBaseModule

internal actual class TimelineReactModule(
    reactContext: ReactApplicationContext,
    private val bridge: TimelineBridge
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "TimelineModule"

    override fun getConstants() = mutableMapOf<String, Any>(
        "EVENT_APPEND_STATUS" to "EVENT_APPEND_STATUS"
    )

    @ReactMethod
    fun openAuthentication() = bridge.openAuthentication()
    @ReactMethod
    fun fetchOwnAccounts(promise: Promise) = promise.resolve(Arguments.makeNativeArray(bridge.fetchOwnAccounts()))
    @ReactMethod
    fun switchAccount(host: String, id: String) = bridge.switchAccount(host, id)
}
