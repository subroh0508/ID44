package id44.mizuki.timeline.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import id44.mizuki.commons.viewmodel.RequireAuthViewModel
import id44.mizuki.commons.viewmodel.runAuthCatching
import id44.mizuki.domain.timeline.usecase.FetchStatusesUseCase
import id44.mizuki.domain.timeline.usecase.SubmitStatusUseCase
import id44.mizuki.domain.timeline.usecase.ToggleFavouriteUseCase
import id44.mizuki.domain.timeline.usecase.ToggleReblogUseCase
import id44.mizuki.shared.model.status.Status
import id44.mizuki.shared.model.status.StatusVisibility
import id44.mizuki.shared.model.status.Stream
import kotlinx.coroutines.launch
import kotlinx.serialization.Properties

class StatusViewModel(
    requireAuthViewModel: RequireAuthViewModel,
    private val fetchStatusesUseCase: FetchStatusesUseCase,
    private val submitStatusUseCase: SubmitStatusUseCase,
    private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
    private val toggleReblogUseCase: ToggleReblogUseCase
) : ViewModel(), RequireAuthViewModel by requireAuthViewModel {
    fun fetchStatuses(stream: Stream, maxId: String? = null, promise: Promise) = viewModelScope.launch {
        runAuthCatching { fetchStatusesUseCase.execute(stream, maxId) }
            .onSuccess { statuses ->
                promise.resolve(Arguments.makeNativeArray(statuses.map {
                    Properties.storeNullable(
                        Status.serializer(),
                        it
                    )
                }))
            }
            .onFailure(promise::reject)
    }

    fun submitStatus(status: String, visibility: StatusVisibility, warningText: String?, promise: Promise) =
        viewModelScope.launch {
            runAuthCatching { submitStatusUseCase.execute(status, visibility, warningText) }
                .onSuccess {
                    promise.resolve(Arguments.makeNativeMap(Properties.storeNullable(Status.serializer(), it)))
                }
                .onFailure(promise::reject)
        }

    fun toggleFavourite(status: Status, promise: Promise) = viewModelScope.launch {
        runAuthCatching { toggleFavouriteUseCase.execute(status) }
            .onSuccess {
                promise.resolve(Arguments.makeNativeMap(Properties.storeNullable(Status.serializer(), it)))
            }
            .onFailure(promise::reject)
    }

    fun toggleReblog(status: Status, promise: Promise) = viewModelScope.launch {
        runAuthCatching { toggleReblogUseCase.execute(status) }
            .onSuccess { promise.resolve(Arguments.makeNativeMap(Properties.storeNullable(Status.serializer(), it))) }
            .onFailure(promise::reject)
    }

    class Factory(
        private val requireAuthViewModel: RequireAuthViewModel,
        private val fetchStatusesUseCase: FetchStatusesUseCase,
        private val submitStatusUseCase: SubmitStatusUseCase,
        private val toggleFavouriteUseCase: ToggleFavouriteUseCase,
        private val toggleReblogUseCase: ToggleReblogUseCase
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T =
            StatusViewModel(
                requireAuthViewModel,
                fetchStatusesUseCase,
                submitStatusUseCase,
                toggleFavouriteUseCase,
                toggleReblogUseCase
            ) as T
    }
}
