package id44.mizuki.auth

import androidx.lifecycle.LiveData

interface AuthenticationContract {
    interface View {
        fun startOauth2Flow()

        fun openAuthorizePage(url: String)

        fun bindAccessToken(accessToken: String)

        fun showErrorMessage(message: String)
    }

    interface Presenter {
        fun onClickAuthorize()

        fun onNewIntent(code: String?, error: String?)

        suspend fun fetchAuthorizeCode(hostName: String, clientName: String, redirectUri: String): String

        suspend fun requestAccessToken(hostName: String, redirectUri: String, code: String): String

        fun onRequestedAccessToken(accessToken: String)

        fun notifyBrowserNotFound()
    }

    interface Model {
        val hostName: LiveData<String>
        val accessToken: LiveData<String>

        fun bindHostName(hostName: String)

        fun bindAccessToken(accessToken: String)
    }
}
