package id44.mizuki.libraries.timeline.domain.usecase.subscribe

import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.shared.valueobject.AccountId
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

internal class TimelineSubscribeUseCaseImpl(
    private val repository: StreamingRepository,
    private val accessTokenRepository: AccessTokenRepository
) : TimelineSubscribeUseCase {
    override suspend fun execute(host: HostName, accountId: AccountId, stream: Stream) = withContext(Dispatchers.Default) {
        val token = accessTokenRepository.getAccessToken(host, accountId)

        repository.openSubscription(host, token, stream)
    }
}
