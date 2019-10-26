package id44.mizuki.timeline.reactnative

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule

class TimelineActivityReactModule(
    reactContext: ReactApplicationContext
    // private val viewModel: TimelineContract.Model,
    //private val presenter: TimelineContract.Presenter
) : ReactContextBaseJavaModule(reactContext) {
    override fun getName() = "TimelineContract"

    override fun getConstants() = mutableMapOf<String, Any>(
        EVENT_APPEND_STATUS to EVENT_APPEND_STATUS
    )
}