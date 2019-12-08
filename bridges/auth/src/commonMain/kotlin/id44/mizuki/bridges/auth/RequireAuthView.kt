package id44.mizuki.bridges.auth

import kotlinx.coroutines.CoroutineScope

interface RequireAuthView : CoroutineScope {
    fun openAuthentication()
}
