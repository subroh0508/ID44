package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri

interface RequestAccessTokenUseCase {
    suspend fun execute(hostName: HostName, redirectUri: Uri, code: String): AccessToken
}
