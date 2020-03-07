package id44.mizuki.libraries.auth.domain.usecase.requestappcredential

import id44.mizuki.shared.valueobject.HostName
import id44.mizuki.shared.valueobject.Uri

interface RequestAppCredentialUseCase {
    suspend fun execute(hostName: HostName, clientName: String, redirectUri: Uri): Uri
}
