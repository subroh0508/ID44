package id44.mizuki.libraries.domain.usecase.fetchstatuses

import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchStatusesUseCaseImpl(
    private val repository: StatusRepository
) : FetchStatusesUseCase {
    override suspend fun execute(stream: Stream, maxId: String?, limit: Int) = withContext(Dispatchers.Default) {
        when (stream) {
            Stream.LOCAL -> repository.getLocalStatuses(maxId, limit)
            Stream.GLOBAL -> repository.getPublicStatuses(maxId, limit)
            else -> listOf()
        }
    }
}
