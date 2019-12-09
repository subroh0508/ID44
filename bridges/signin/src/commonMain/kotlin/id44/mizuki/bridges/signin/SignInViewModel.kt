package id44.mizuki.bridges.signin

import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri

interface SignInViewModel {
    fun onChangeHostName(host: HostName)

    fun startOauth2Flow(clientName: String, redirectUri: Uri)
}
