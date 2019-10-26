package id44.mizuki.timeline.reactnative

import com.facebook.react.bridge.ReactContext
import com.facebook.react.modules.core.DeviceEventManagerModule
import id44.mizuki.libraries.timeline.domain.entity.Status

internal fun emit(
    context: ReactContext?,
    status: Status
) {
    context ?: return

    val map = mapOf(
        "id" to status.id,
        "content" to status.content,
        "favouriteCount" to status.favouriteCount,
        "reblogCount" to status.reblogCount,
        "tooter" to mapOf(
            "id" to status.tooter.id,
            "username" to status.tooter.usename,
            "avatar" to status.tooter.avatarStatic
        )
    )

    context.getJSModule(DeviceEventManagerModule.RCTDeviceEventEmitter::class.java)
        .emit("appendStatus", map)
}