package id44.mizuki.timeline.reactnative

import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DeviceEventManagerModule
import id44.mizuki.libraries.timeline.domain.entity.Status

internal const val EVENT_APPEND_STATUS = "EVENT_APPEND_STATUS"

internal fun emit(
    context: ReactContext?,
    status: Status
) {
    context ?: return

    val map = Arguments.makeNativeMap(mapOf(
        "id" to status.id,
        "content" to status.content,
        "favouriteCount" to status.favouriteCount,
        "reblogCount" to status.reblogCount,
        "tooter" to mapOf(
            "id" to status.tooter.id,
            "username" to status.tooter.usename,
            "avatar" to status.tooter.avatarStatic
        )
    ))

    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
        .emit(EVENT_APPEND_STATUS, map)
}