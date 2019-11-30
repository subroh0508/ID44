package id44.mizuki.libraries.api

import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName

object Credentials {
    internal val nowHost: HostName get() = host ?: throw NullPointerException()
    internal val nowToken: AccessToken get() = token ?: throw NullPointerException()

    private var host: HostName? = null
    private var token: AccessToken? = null

    fun setNowCredentials(host: HostName, token: AccessToken) {
        this.host = host
        this.token = token
    }
}
