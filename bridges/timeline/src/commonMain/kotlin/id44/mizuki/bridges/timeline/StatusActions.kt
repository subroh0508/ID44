package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthActions
import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.reactnativesupport.ReactArray
import id44.mizuki.libraries.reactnativesupport.reactArray
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.launch
import kotlinx.serialization.Mapper

class StatusActions(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val fetchStatusesUseCase: FetchStatusesUseCase
) : RequireAuthActions(view, accessTokenRepository) {
    fun fetchStatuses(
        stream: Stream, maxId: String? = null,
        resolve: (ReactArray) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { fetchStatusesUseCase.execute(stream, maxId) }
            .onSuccess { statuses ->
                resolve.invoke(reactArray(
                    statuses.map { Mapper.map(Status.serializer(), it) }
                ))
            }
            .onFailure(reject)
    }
}
