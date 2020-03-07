package id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses

import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.Stream

interface FetchStatusesUseCase {
    suspend fun execute(stream: Stream, maxId: String? = null, limit: Int = 20): List<Status>
}
