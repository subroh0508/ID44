package id44.mizuki.timeline

import android.os.Bundle
import com.facebook.react.bridge.Arguments
import com.facebook.react.modules.core.DeviceEventManagerModule
import id44.mizuki.commons.RequireAuthReactActivity
import id44.mizuki.timeline.di.inject
import id44.mizuki.timeline.viewmodel.StreamingViewModel
import org.kodein.di.Kodein
import org.kodein.di.generic.instance

class TimelineActivity : RequireAuthReactActivity() {
    override lateinit var kodein: Kodein

    val streamingViewModel: StreamingViewModel by instance()

    override fun getMainComponentName(): String = "Timeline"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)
    }

    private val emitter: DeviceEventManagerModule.RCTDeviceEventEmitter?
        get() = reactInstanceManager.currentReactContext?.getJSModule(
            DeviceEventManagerModule.RCTDeviceEventEmitter::class.java
        )
}
