package id44.mizuki.bridges.timeline

import kotlinx.coroutines.CoroutineScope

interface TimelineView : CoroutineScope {
    fun openAuthentication()

    fun restart()

    fun emitStatus(key: String, status: Map<String, Any>)
}
