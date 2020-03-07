package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream

interface SubmitStatusUseCase {
    suspend fun execute(
        status: String,
        visibility: StatusVisibility, warningText: String? = null
    ): Status
}
