package id44.mizuki.libraries.auth.domain.usecase.requestappcredential

import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri

interface RequestAppCredentialUseCase {
    suspend fun execute(hostName: HostName, clientName: String, redirectUri: Uri): Uri
}
