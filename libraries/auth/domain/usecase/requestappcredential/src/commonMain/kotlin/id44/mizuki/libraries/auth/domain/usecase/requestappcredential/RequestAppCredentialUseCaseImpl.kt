package id44.mizuki.libraries.auth.domain.usecase.requestappcredential

import id44.mizuki.infra.auth.repository.AccountCredentialRepository
import id44.mizuki.infra.auth.repository.AppCredentialRepository
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestAppCredentialUseCaseImpl(
    private val appCredentialRepository: AppCredentialRepository,
    private val accountCredentialRepository: AccountCredentialRepository
) : RequestAppCredentialUseCase {
    override suspend fun execute(
        hostName: HostName,
        clientName: String,
        redirectUri: Uri
    ): Uri = withContext(Dispatchers.Default) {
        val (clientId, clientSecret) = appCredentialRepository.fetchAppCredential(hostName, clientName, redirectUri)

        appCredentialRepository.cacheAppCredential(hostName, clientId, clientSecret)

        accountCredentialRepository.buildAuthorizeUrl(hostName, clientId, clientSecret, redirectUri)
    }
}
