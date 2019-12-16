package id44.mizuki.timeline

import android.os.Bundle
import com.facebook.react.bridge.Arguments
import id44.mizuki.auth.RequireAuthReactActivity
import id44.mizuki.bridges.timeline.TimelineView
import id44.mizuki.timeline.di.TimelineActivityComponent
import id44.mizuki.timeline.di.inject

class TimelineActivity : RequireAuthReactActivity(), TimelineView {
    override fun getMainComponentName(): String = "Timeline"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)
    }

    override fun restart() {
        finish()
        startActivity(intent)
    }

    override fun emitStatus(key: String, status: Map<String, Any>) =
            emitter?.emit(key, Arguments.makeNativeMap(status)) ?: Unit

    internal lateinit var timelineActivityComponent: TimelineActivityComponent
}