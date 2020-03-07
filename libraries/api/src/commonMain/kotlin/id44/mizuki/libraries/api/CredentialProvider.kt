package id44.mizuki.libraries.api

import id44.mizuki.shared.valueobject.AccessToken
import id44.mizuki.shared.valueobject.HostName

interface CredentialProvider {
    val nowHost: HostName
    val nowToken: AccessToken
}
