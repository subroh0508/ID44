package id44.mizuki.timeline

import android.os.Bundle
import android.util.Log
import id44.mizuki.base.ui.ScopedActivity
import id44.mizuki.libraries.timeline.domain.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.valueobject.Stream
import id44.mizuki.timeline.di.TimelineActivityComponent
import id44.mizuki.timeline.di.inject
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class TimelineActivity : ScopedActivity() {
    @Inject
    lateinit var subscribeUseCase: TimelineSubscribeUseCase
    @Inject
    lateinit var unsubscribeUseCase: TimelineUnsubscribeUseCase

    private val hostName: String
        get() = intent.getStringExtra("hostname") ?: throw IllegalStateException()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        inject()

        setContentView(R.layout.activity_timeline)
    }

    override fun onResume() {
        super.onResume()

        launch(coroutineContext) {
            subscribeUseCase.execute(hostName, Stream.LOCAL)
                .collect {
                    Log.d("status", it.toString())
                }
        }
    }

    override fun onPause() {
        super.onPause()

        unsubscribeUseCase.execute("imastodon.net", Stream.LOCAL)
    }

    internal lateinit var timelineActivityComponent: TimelineActivityComponent
}
