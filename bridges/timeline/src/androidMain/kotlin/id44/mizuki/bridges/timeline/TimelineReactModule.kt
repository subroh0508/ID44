package id44.mizuki.bridges.timeline

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.shared.reactnative.ReactContextBaseModule
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

internal actual class TimelineReactModule(
    reactContext: ReactApplicationContext,
    private val ownAccountsBridge: OwnAccountsBridge,
    private val timelineBridge: TimelineBridge
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "TimelineModule"

    override fun getConstants() = mutableMapOf(
        EVENT_APPEND_STATUS to EVENT_APPEND_STATUS,
        STREAM to Stream.values().map {
            it.toString() to it.toString()
        }.toMap()
    )

    @ReactMethod
    fun openAuthentication() = ownAccountsBridge.openAuthentication()
    @ReactMethod
    fun fetchOwnAccounts(promise: Promise) = promise.resolve(Arguments.makeNativeArray(ownAccountsBridge.fetchOwnAccounts()))
    @ReactMethod
    fun switchAccount(host: String, id: String) = ownAccountsBridge.switchAccount(HostName(host), AccountId(id))

    @ReactMethod
    fun subscribe(host: String, id: String, stream: String, promise: Promise) =
        timelineBridge.subscribe(HostName(host), AccountId(id), Stream.valueOf(stream), promise)
    @ReactMethod
    fun unsubscribe(host: String, id: String, stream: String, promise: Promise) =
        timelineBridge.unsubscribe(HostName(host), AccountId(id), Stream.valueOf(stream), promise)
}
