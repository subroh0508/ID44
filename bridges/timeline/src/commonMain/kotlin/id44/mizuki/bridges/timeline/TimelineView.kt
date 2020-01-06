package id44.mizuki.bridges.timeline
import id44.mizuki.bridges.auth.RequireAuthView

interface TimelineView : RequireAuthView {
    fun emitStatus(key: String, streamKey: String, status: Map<String, Any?>)
}
