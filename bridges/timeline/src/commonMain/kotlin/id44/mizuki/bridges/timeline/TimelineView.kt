package id44.mizuki.bridges.timeline
import id44.mizuki.bridges.auth.RequireAuthView

interface TimelineView : RequireAuthView {
    fun restart()

    fun onSubscribe()

    fun onUnsubscribe()

    fun emitStatus(key: String, status: Map<String, Any>)
}
