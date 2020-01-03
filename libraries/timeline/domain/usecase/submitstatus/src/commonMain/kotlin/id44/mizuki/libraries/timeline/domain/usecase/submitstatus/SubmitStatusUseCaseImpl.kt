package id44.mizuki.libraries.timeline.domain.usecase.submitstatus

import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.valueobject.StatusVisibility
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class SubmitStatusUseCaseImpl(
    private val repository: StatusRepository
) : SubmitStatusUseCase {
    override suspend fun execute(
        status: String,
        visibility: StatusVisibility, warningText: String?
    ) = withContext(Dispatchers.Default) {
        repository.postStatus(status, visibility, warningText)
    }
}
