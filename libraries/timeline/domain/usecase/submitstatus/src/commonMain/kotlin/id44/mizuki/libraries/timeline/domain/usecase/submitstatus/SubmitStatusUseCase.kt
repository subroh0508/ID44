package id44.mizuki.libraries.timeline.domain.usecase.submitstatus

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.StatusVisibility
import id44.mizuki.libraries.timeline.domain.valueobject.Stream

interface SubmitStatusUseCase {
    suspend fun execute(
        status: String,
        visibility: StatusVisibility, warningText: String? = null
    ): Status
}
