package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.status.Status

interface ToggleFavouriteUseCase {
    suspend fun execute(status: Status): Status
}
