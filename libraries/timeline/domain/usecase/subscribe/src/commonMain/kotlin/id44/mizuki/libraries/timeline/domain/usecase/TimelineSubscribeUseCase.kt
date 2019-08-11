package id44.mizuki.libraries.timeline.domain.usecase

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface TimelineSubscribeUseCase {
    suspend fun execute(hostName: String, stream: Stream): Status
}