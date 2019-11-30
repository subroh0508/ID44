package id44.mizuki.timeline.presentation.ui

import android.os.Bundle
import id44.mizuki.auth.presentation.ui.RequireAuthReactActivity
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.timeline.di.TimelineActivityComponent
import id44.mizuki.timeline.di.inject
import id44.mizuki.timeline.reactnative.emit
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimelineActivity : RequireAuthReactActivity() {
    @Inject
    lateinit var subscribeUseCase: TimelineSubscribeUseCase
    @Inject
    lateinit var unsubscribeUseCase: TimelineUnsubscribeUseCase

    private val hostName: String
        get() = intent.getStringExtra("hostname") ?: "pawoo.net"

    override fun getMainComponentName(): String = "Timeline"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)
    }

    override fun onResume() {
        super.onResume()

        launch {
            subscribeUseCase.execute(Stream.LOCAL)
                .collect {
                    emit(reactInstanceManager.currentReactContext, it)
                    // Log.d("status", it.toString())
                }
        }
    }

    override fun onPause() {
        super.onPause()

        try {
            unsubscribeUseCase.execute(Stream.LOCAL)
        } catch (e: Throwable) {

        }
    }

    internal lateinit var timelineActivityComponent: TimelineActivityComponent
}
