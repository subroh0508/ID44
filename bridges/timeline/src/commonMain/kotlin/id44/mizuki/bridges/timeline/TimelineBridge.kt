package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthBridge
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.shared.reactnative.ReactPromise
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class TimelineBridge(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val timelineSubscribeUseCase: TimelineSubscribeUseCase,
    private val timelineUnsubscribeUseCase: TimelineUnsubscribeUseCase
) : RequireAuthBridge(view, accessTokenRepository) {
    fun subscribe(stream: Stream, promise: ReactPromise) = view.launch {
        runCatching { timelineSubscribeUseCase.execute(stream)?.collect { view.emitStatus(EVENT_APPEND_STATUS, it.toMap()) } }
            .onSuccess { promise.resolve(null) }
            .onHttpFailure { e -> promise.reject(e) }
    }

    fun unsubscribe(stream: Stream) = timelineUnsubscribeUseCase.execute(stream)

    private fun Status.toMap() = mapOf(
        "id" to id,
        "content" to content,
        "favouriteCount" to favouriteCount,
        "reblogCount" to reblogCount,
        "tooter" to mapOf(
            "id" to tooter.id,
            "username" to tooter.usename,
            "avatar" to tooter.avatarStatic
        )
    )
}
