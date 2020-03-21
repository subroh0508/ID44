package id44.mizuki.timeline.di

import com.facebook.react.bridge.BaseJavaModule
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.timeline.viewmodel.OwnAccountsViewModel
import id44.mizuki.timeline.viewmodel.StreamingViewModel

class TimelineReactModule(
    private val streamingViewModel: StreamingViewModel,
    private val ownAccountsViewModel: OwnAccountsViewModel
) : BaseJavaModule() {
    companion object {
        private const val NAME = "TimelineNativeActions"
    }

    override fun getName() = NAME

    override fun getConstants() = mutableMapOf(
        EVENT_APPEND_STATUS to EVENT_APPEND_STATUS,
        STREAM to Stream.values().map {
            it.toString() to it.toString()
        }.toMap(),
        STATUS_VISIBILITY to StatusVisibility.values().map {
            it.toString() to it.toString()
        }.toMap()
    )

    @ReactMethod
    fun subscribe(host: String, id: String, stream: String, promise: Promise) {
        streamingViewModel.subscribe(host, id, stream, promise)
    }

    @ReactMethod
    fun unsubscribe(host: String, id: String, stream: String, promise: Promise) {
        streamingViewModel.unsubscribe(host, id, stream, promise)
    }

    @ReactMethod
    fun openAuthentication() {
        ownAccountsViewModel.openAuthentication()
    }

    @ReactMethod
    fun fetchOwnAccount(promise: Promise) {
        ownAccountsViewModel.fetchOwnAccount(promise)
    }

    @ReactMethod
    fun fetchOwnAccounts(promise: Promise) {
        ownAccountsViewModel.fetchOwnAccounts(promise)
    }

    @ReactMethod
    fun switchAccount(host: String, id: String) {
        ownAccountsViewModel.switchAccount(host, id)
    }
}
