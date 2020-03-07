package id44.mizuki.domain.signin.usecase

import id44.mizuki.infra.auth.repository.AccountCredentialRepository
import id44.mizuki.infra.auth.repository.AppCredentialRepository
import id44.mizuki.shared.util.valueobject.AccessToken
import id44.mizuki.shared.util.valueobject.HostName
import id44.mizuki.shared.util.valueobject.Uri
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestAccessTokenUseCaseImpl(
    private val appCredentialRepository: AppCredentialRepository,
    private val accountCredentialRepository: AccountCredentialRepository
) : RequestAccessTokenUseCase {
    override suspend fun execute(
        hostName: HostName,
        redirectUri: Uri,
        code: String
    ): AccessToken = withContext(Dispatchers.Default) {
        val clientId = appCredentialRepository.getClientId(hostName) ?: throw IllegalStateException()
        val clientSecret = appCredentialRepository.getClientSecret(hostName) ?: throw IllegalStateException()

        val accessToken = accountCredentialRepository.fetchAccessToken(hostName, clientId, clientSecret, redirectUri, code)

        accountCredentialRepository.saveOwnAccount(hostName, accessToken)

        accessToken
    }
}
