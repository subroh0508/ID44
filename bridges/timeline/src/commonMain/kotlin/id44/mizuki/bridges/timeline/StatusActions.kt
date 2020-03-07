package id44.mizuki.bridges.timeline

import id44.mizuki.bridges.auth.RequireAuthActions
import id44.mizuki.infra.auth.repository.AccessTokenRepository
import id44.mizuki.libraries.reactnativesupport.ReactArray
import id44.mizuki.libraries.reactnativesupport.ReactMap
import id44.mizuki.libraries.reactnativesupport.reactArray
import id44.mizuki.libraries.reactnativesupport.reactMap
import id44.mizuki.shared.model.status.Status
import id44.mizuki.domain.timeline.usecase.FetchStatusesUseCase
import id44.mizuki.domain.timeline.usecase.SubmitStatusUseCase
import id44.mizuki.domain.timeline.usecase.ToggleFavouriteUseCase
import id44.mizuki.domain.timeline.usecase.ToggleReblogUseCase
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream
import kotlinx.coroutines.launch
import kotlinx.serialization.Mapper

class StatusActions(
    private val view: TimelineView, accessTokenRepository: AccessTokenRepository,
    private val fetchStatusesUseCase: FetchStatusesUseCase,
    private val submitStatusUseCase: SubmitStatusUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val toggleReblogUseCase: ToggleReblogUseCase
) : RequireAuthActions(view, accessTokenRepository) {
    fun fetchStatuses(
        stream: Stream, maxId: String? = null,
        resolve: (ReactArray) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { fetchStatusesUseCase.execute(stream, maxId) }
            .onSuccess { statuses ->
                resolve.invoke(reactArray(
                    statuses.map { Mapper.mapNullable(Status.serializer(), it) }
                ))
            }
            .onHttpFailure(reject)
    }

    fun submitStatus(
        status: String, visibility: StatusVisibility, warningText: String?,
        resolve: (ReactMap) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { submitStatusUseCase.execute(status, visibility, warningText) }
            .onSuccess { resolve(reactMap(Mapper.mapNullable(Status.serializer(), it))) }
            .onHttpFailure(reject)
    }

    fun toggleFavourite(
        status: Status,
        resolve: (ReactMap) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { toggleFavouriteUseCase.execute(status) }
            .onSuccess { resolve(reactMap(Mapper.mapNullable(Status.serializer(), it))) }
            .onHttpFailure(reject)
    }

    fun toggleReblog(
        status: Status,
        resolve: (ReactMap) -> Unit, reject: (Throwable) -> Unit
    ) = view.launch {
        runCatching { toggleReblogUseCase.execute(status) }
            .onSuccess { resolve(reactMap(Mapper.mapNullable(Status.serializer(), it))) }
            .onHttpFailure(reject)
    }
}
