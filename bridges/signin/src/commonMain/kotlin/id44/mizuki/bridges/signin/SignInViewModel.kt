package id44.mizuki.bridges.signin

import id44.mizuki.libraries.shared.valueobject.HostName
import kotlinx.coroutines.CoroutineScope

interface SignInViewModel {
    val scope: CoroutineScope

    suspend fun startOauth2Flow(host: HostName)
}
