package id44.mizuki.authentication.presentation

import androidx.lifecycle.LiveData
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri

interface AuthenticationContract {
    interface View {
        fun startOauth2Flow()

        fun openAuthorizePage(url: Uri)

        fun bindAccessToken(token: AccessToken)

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun onClickAuthorize()

        fun onNewIntent(code: String?, error: String?)

        suspend fun fetchAuthorizeCode(hostName: HostName, clientName: String, redirectUri: Uri): String

        suspend fun requestAccessToken(hostName: HostName, redirectUri: Uri, code: String): AccessToken

        fun onRequestedAccessToken(token: AccessToken)

        fun notifyBrowserNotFound()
    }

    interface Model {
        val hostName: LiveData<HostName>
        val accessToken: LiveData<AccessToken>

        fun bindHostName(hostName: HostName)

        fun bindAccessToken(token: AccessToken)
    }
}
