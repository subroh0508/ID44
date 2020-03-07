package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream

interface ToggleReblogUseCase {
    suspend fun execute(status: Status): Status
}
