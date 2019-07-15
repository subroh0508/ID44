package id44.mizuki.auth

interface AuthenticationConstract {
    interface View

    interface Presenter {
        suspend fun buildAuthorizeUrl(hostName: String): String

        suspend fun requestAccessToken(hostName: String, code: String): String
    }
}