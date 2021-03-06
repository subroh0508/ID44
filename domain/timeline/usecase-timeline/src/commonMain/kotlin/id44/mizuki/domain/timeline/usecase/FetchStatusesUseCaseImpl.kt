package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.status.Stream
import id44.mizuki.infra.status.repository.StatusRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class FetchStatusesUseCaseImpl(
    private val repository: StatusRepository
) : FetchStatusesUseCase {
    override suspend fun execute(stream: Stream, maxId: String?, limit: Int) = withContext(Dispatchers.Default) {
        when (stream) {
            Stream.GLOBAL -> repository.getGlobalStatuses(maxId, limit)
            Stream.LOCAL -> repository.getLocalStatuses(maxId, limit)
            Stream.HOME -> repository.getHomeStatuses(maxId, limit)
            else -> listOf()
        }
    }
}
