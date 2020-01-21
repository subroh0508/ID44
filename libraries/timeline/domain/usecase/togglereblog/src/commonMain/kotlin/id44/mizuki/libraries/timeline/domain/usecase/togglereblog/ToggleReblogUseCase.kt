package id44.mizuki.libraries.timeline.domain.usecase.togglereblog

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface ToggleReblogUseCase {
    suspend fun execute(status: Status): Status
}
