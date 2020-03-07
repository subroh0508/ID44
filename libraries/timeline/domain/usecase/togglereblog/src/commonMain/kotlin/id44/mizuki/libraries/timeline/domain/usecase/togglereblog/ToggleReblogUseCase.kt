package id44.mizuki.libraries.timeline.domain.usecase.togglereblog

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream

interface ToggleReblogUseCase {
    suspend fun execute(status: Status): Status
}
