package id44.mizuki.libraries.timeline.domain.usecase.submitstatus

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.infra.status.repository.StatusRepository
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
