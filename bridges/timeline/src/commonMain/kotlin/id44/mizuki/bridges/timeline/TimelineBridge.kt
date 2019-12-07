package id44.mizuki.bridges.timeline

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

internal class TimelineBridge(
    private val view: TimelineView,
    private val timelineSubscribeUseCase: TimelineSubscribeUseCase,
    private val timelineUnsubscribeUseCase: TimelineUnsubscribeUseCase
) {
    fun subscribe(stream: Stream) = view.launch {
        timelineSubscribeUseCase.execute(stream)
            ?.collect { view.emitStatus(EVENT_APPEND_STATUS, it.toMap()) }
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
