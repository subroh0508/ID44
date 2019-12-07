package id44.mizuki.timeline.presentation.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.auth.presentation.viewmodel.RequireAuthViewModel
import id44.mizuki.libraries.timeline.domain.entity.Status
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

class TimelineViewModel(
    private val subscribeUseCase: TimelineSubscribeUseCase,
    private val unsubscribeUseCase: TimelineUnsubscribeUseCase,
    authViewModel: RequireAuthViewModel
) : ViewModel(), RequireAuthContract.ViewModel by authViewModel {
    val status: LiveData<Status> = liveData {
        subscribeUseCase.execute(Stream.LOCAL)?.collect { emit(it) }
    }

    override fun onCleared() = unsubscribeUseCase.execute(Stream.LOCAL)

    class Factory @Inject constructor(
        private val subscribeUseCase: TimelineSubscribeUseCase,
        private val unsubscribeUseCase: TimelineUnsubscribeUseCase,
        private val authViewModel: RequireAuthViewModel
    ) : ViewModelProvider.NewInstanceFactory() {
        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel?> create(modelClass: Class<T>): T = TimelineViewModel(
            subscribeUseCase, unsubscribeUseCase, authViewModel
        ) as T
    }
}
