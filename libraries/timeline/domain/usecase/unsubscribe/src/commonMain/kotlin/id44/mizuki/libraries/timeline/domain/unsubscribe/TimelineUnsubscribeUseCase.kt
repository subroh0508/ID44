package id44.mizuki.libraries.timeline.domain.unsubscribe

import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface TimelineUnsubscribeUseCase {
    fun execute(hostName: String, stream: Stream)
}
