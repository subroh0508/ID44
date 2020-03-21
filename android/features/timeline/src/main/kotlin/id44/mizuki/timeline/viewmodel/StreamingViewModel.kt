package id44.mizuki.timeline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.modules.core.DeviceEventManagerModule
import id44.mizuki.commons.viewmodel.RequireAuthViewModel
import id44.mizuki.commons.viewmodel.runAuthCatching
import id44.mizuki.domain.timeline.usecase.TimelineSubscribeUseCase
import id44.mizuki.domain.timeline.usecase.TimelineUnsubscribeUseCase
import id44.mizuki.infra.auth.repository.AccessTokenRepository
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.timeline.di.EVENT_APPEND_STATUS
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.Properties

class StreamingViewModel(
    requireAuthViewModel: RequireAuthViewModel,
    private val emitter: DeviceEventManagerModule.RCTDeviceEventEmitter?,
    private val timelineSubscribeUseCase: TimelineSubscribeUseCase,
    private val timelineUnsubscribeUseCase: TimelineUnsubscribeUseCase
) : ViewModel(), RequireAuthViewModel by requireAuthViewModel {
    fun subscribe(host: String, id: String, stream: String, promise: Promise) = viewModelScope.launch {
        runAuthCatching { timelineSubscribeUseCase.execute(HostName(host), AccountId(id), Stream.valueOf(stream)) }
            .onSuccess { flow ->
                promise.resolve(null)

                flow?.collect { status ->
                    val payload = mapOf(
                        "streamKey" to streamKey(host, id, stream),
                        "status" to Properties.storeNullable(Status.serializer(), status)
                    )

                    emitter?.emit(EVENT_APPEND_STATUS, Arguments.makeNativeMap(payload))
                }
            }
            .onFailure(promise::reject)
    }

    fun unsubscribe(host: String, id: String, stream: String, promise: Promise) = viewModelScope.launch {
        runAuthCatching { timelineUnsubscribeUseCase.execute(HostName(host), AccountId(id), Stream.valueOf(stream)) }
            .onSuccess { promise.resolve(null) }
            .onFailure(promise::reject)
    }

    private fun streamKey(host: String, id: String, stream: String) = "$host/$id/$stream"

    class Factory(
        private val requireAuthViewModel: RequireAuthViewModel,
        private val emitter: DeviceEventManagerModule.RCTDeviceEventEmitter?,
        private val timelineSubscribeUseCase: TimelineSubscribeUseCase,
        private val timelineUnsubscribeUseCase: TimelineUnsubscribeUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            StreamingViewModel(
                requireAuthViewModel,
                emitter,
                timelineSubscribeUseCase,
                timelineUnsubscribeUseCase
            ) as T
    }
}
