package id44.mizuki.libraries.timeline.domain.usecase.unsubscribe

import id44.mizuki.infra.auth.repository.AccessTokenRepository
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.model.status.Stream
import id44.mizuki.infra.status.repository.StreamingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class TimelineUnsubscribeUseCaseImpl(
    private val repository: StreamingRepository,
    private val accessTokenRepository: AccessTokenRepository
) : TimelineUnsubscribeUseCase {
    override suspend fun execute(host: HostName, accountId: AccountId, stream: Stream) = withContext(Dispatchers.Default) {
        val token = accessTokenRepository.getAccessToken(host, accountId)

        repository.closeSubscription(host, token, stream)
    }
}
