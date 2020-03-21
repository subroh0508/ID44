package id44.mizuki.timeline.di

import com.facebook.react.bridge.BaseJavaModule
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactMethod
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.timeline.viewmodel.OwnAccountsViewModel
import id44.mizuki.timeline.viewmodel.StatusViewModel
import id44.mizuki.timeline.viewmodel.StreamingViewModel
import kotlinx.serialization.json.Json

class TimelineReactModule(
    private val streamingViewModel: StreamingViewModel,
    private val statusViewModel: StatusViewModel,
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
    fun fetchStatuses(stream: String, maxId: String? = null, promise: Promise) {
        statusViewModel.fetchStatuses(Stream.valueOf(stream), maxId, promise)
    }

    @ReactMethod
    fun submitStatus(status: String, warningText: String?, visibility: String, promise: Promise) {
        statusViewModel.submitStatus(status, StatusVisibility.valueOf(visibility), warningText, promise)
    }

    @ReactMethod
    fun toggleFavourite(status: String, promise: Promise) {
        statusViewModel.toggleFavourite(Json.nonstrict.parse(Status.serializer(), status), promise)
    }

    @ReactMethod
    fun toggleReblog(status: String, promise: Promise) {
        statusViewModel.toggleReblog(Json.nonstrict.parse(Status.serializer(), status), promise)
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
