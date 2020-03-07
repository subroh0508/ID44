package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthActions
import id44.mizuki.infra.auth.repository.AccessTokenRepository
import id44.mizuki.shared.util.valueobject.AccountId
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.model.status.Status
import id44.mizuki.domain.timeline.usecase.TimelineSubscribeUseCase
import id44.mizuki.domain.timeline.usecase.TimelineUnsubscribeUseCase
import id44.mizuki.shared.model.status.Stream
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.serialization.Mapper

internal class SubscribeActions(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val timelineSubscribeUseCase: TimelineSubscribeUseCase,
    private val timelineUnsubscribeUseCase: TimelineUnsubscribeUseCase
) : RequireAuthActions(view, accessTokenRepository) {
    fun subscribe(
        host: HostName, accountId: AccountId, stream: Stream,
        resolve: (Any?) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { timelineSubscribeUseCase.execute(host, accountId, stream) }
            .onSuccess { flow ->
                resolve(null)

                flow?.collect {
                    view.emitStatus(
                        EVENT_APPEND_STATUS,
                        "${host.value}/${accountId.value}/${stream.name}",
                        Mapper.mapNullable(Status.serializer(), it)
                    )
                }
            }
            .onHttpFailure(reject)
    }

    fun unsubscribe(
        host: HostName, accountId: AccountId, stream: Stream,
        resolve: (Any?) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { timelineUnsubscribeUseCase.execute(host, accountId, stream) }
            .onSuccess { resolve(null) }
            .onHttpFailure(reject)
    }
}
