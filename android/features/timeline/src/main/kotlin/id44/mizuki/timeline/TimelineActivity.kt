package id44.mizuki.timeline

import android.os.Bundle
import com.facebook.react.bridge.Arguments
import com.facebook.react.modules.core.DeviceEventManagerModule
import id44.mizuki.auth.RequireAuthReactActivity
import id44.mizuki.bridges.timeline.TimelineView
import id44.mizuki.timeline.di.inject
import org.kodein.di.KodeinAware

class TimelineActivity : RequireAuthReactActivity(), TimelineView, KodeinAware {
    override fun getMainComponentName(): String = "Timeline"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)
    }

    override fun emitStatus(key: String, streamKey: String, status: Map<String, Any?>) =
            emitter?.emit(
                key,
                Arguments.makeNativeMap(
                    mapOf("streamKey" to streamKey, "status" to Arguments.makeNativeMap(status))
                )
            ) ?: Unit

    private val emitter: DeviceEventManagerModule.RCTDeviceEventEmitter?
        get() = reactInstanceManager.currentReactContext?.getJSModule(
            DeviceEventManagerModule.RCTDeviceEventEmitter::class.java
        )
}
