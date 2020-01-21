package id44.mizuki.libraries.timeline.domain.usecase.togglefavourite

import id44.mizuki.libraries.timeline.domain.entity.Status

interface ToggleFavouriteUseCase {
    suspend fun execute(status: Status): Status
}
