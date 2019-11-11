package id44.mizuki.timeline.presentation.ui

import android.os.Bundle
import com.facebook.react.ReactInstanceManager
import id44.mizuki.auth.presentation.ui.RequireAuthActivity
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.timeline.R
import id44.mizuki.timeline.di.TimelineActivityComponent
import id44.mizuki.timeline.di.inject
import id44.mizuki.timeline.reactnative.emit
import kotlinx.android.synthetic.main.activity_timeline.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimelineActivity : RequireAuthActivity() {
    @Inject
    lateinit var subscribeUseCase: TimelineSubscribeUseCase
    @Inject
    lateinit var unsubscribeUseCase: TimelineUnsubscribeUseCase
    @Inject
    lateinit var reactInstanceManager: ReactInstanceManager

    private val hostName: String
        get() = intent.getStringExtra("hostname") ?: "pawoo.net"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_timeline)

        inject()

        reactRootView.startReactApplication(
            reactInstanceManager,
            "Timeline"
        )
    }

    override fun onResume() {
        super.onResume()

        launch(coroutineContext) {
            subscribeUseCase.execute(hostName, Stream.LOCAL)
                .collect {
                    emit(reactInstanceManager.currentReactContext, it)
                    // Log.d("status", it.toString())
                }
        }
    }

    override fun onPause() {
        super.onPause()

        unsubscribeUseCase.execute(hostName, Stream.LOCAL)
    }

    override fun onDestroy() {
        reactRootView.unmountReactApplication()
        reactInstanceManager.destroy()
        super.onDestroy()
    }

    internal lateinit var timelineActivityComponent: TimelineActivityComponent
}
