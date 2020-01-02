package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthBridge
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.reactnativesupport.ReactPromise
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.Mapper

internal class TimelineBridge(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val timelineSubscribeUseCase: TimelineSubscribeUseCase,
    private val timelineUnsubscribeUseCase: TimelineUnsubscribeUseCase
) : RequireAuthBridge(view, accessTokenRepository) {
    fun subscribe(host: HostName, accountId: AccountId, stream: Stream, promise: ReactPromise) = view.launch {
        runCatching { timelineSubscribeUseCase.execute(host, accountId, stream) }
            .onSuccess { flow ->
                promise.resolve(null)

                view.onSubscribe()
                flow?.collect { view.emitStatus(EVENT_APPEND_STATUS, Mapper.map(Status.serializer(), it)) }
            }
            .onHttpFailure(promise::reject)
    }

    fun unsubscribe(host: HostName, accountId: AccountId, stream: Stream, promise: ReactPromise) = view.launch {
        runCatching { timelineUnsubscribeUseCase.execute(host, accountId, stream) }
            .onSuccess {
                promise.resolve(null)

                view.onUnsubscribe()
            }
            .onHttpFailure(promise::reject)
    }
}
