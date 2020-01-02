package id44.mizuki.bridges.timeline

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.libraries.reactnativesupport.ReactContextBaseModule
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

internal actual class TimelineReactModule(
    reactContext: ReactApplicationContext,
    private val ownAccountsActions: OwnAccountsActions,
    private val statusActions: StatusActions,
    private val subscribeActions: SubscribeActions
) : ReactContextBaseModule(reactContext) {
    override fun getName() = "TimelineNativeActions"

    override fun getConstants() = mutableMapOf(
        EVENT_APPEND_STATUS to EVENT_APPEND_STATUS,
        STREAM to Stream.values().map {
            it.toString() to it.toString()
        }.toMap()
    )

    @ReactMethod
    fun openAuthentication() = ownAccountsActions.openAuthentication()
    @ReactMethod
    fun fetchOwnAccount(promise: Promise) = ownAccountsActions.fetchOwnAccount(promise::resolve, promise::reject)
    @ReactMethod
    fun fetchOwnAccounts(promise: Promise) = ownAccountsActions.fetchOwnAccounts(promise::resolve)
    @ReactMethod
    fun switchAccount(host: String, id: String) = ownAccountsActions.switchAccount(HostName(host), AccountId(id))

    @ReactMethod
    fun fetchStatuses(stream: String, maxId: String? = null, promise: Promise) =
        statusActions.fetchStatuses(Stream.valueOf(stream), maxId, promise::resolve, promise::reject)

    @ReactMethod
    fun subscribe(host: String, id: String, stream: String, promise: Promise) =
        subscribeActions.subscribe(HostName(host), AccountId(id), Stream.valueOf(stream), promise::resolve, promise::reject)
    @ReactMethod
    fun unsubscribe(host: String, id: String, stream: String, promise: Promise) =
        subscribeActions.unsubscribe(HostName(host), AccountId(id), Stream.valueOf(stream), promise::resolve, promise::reject)
}
