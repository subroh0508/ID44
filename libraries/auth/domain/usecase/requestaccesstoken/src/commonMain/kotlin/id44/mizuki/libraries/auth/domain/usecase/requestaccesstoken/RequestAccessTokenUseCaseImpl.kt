package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RequestAccessTokenUseCaseImpl(
    private val appCredentialRepository: AppCredentialRepository,
    private val accessTokenRepository: AccessTokenRepository
) : RequestAccessTokenUseCase {
    override suspend fun execute(
        hostName: String,
        redirectUri: String,
        code: String
    ): String = withContext(Dispatchers.Default) {
        val clientId = appCredentialRepository.getClientId(hostName) ?: throw IllegalStateException()
        val clientSecret = appCredentialRepository.getClientSecret(hostName) ?: throw IllegalStateException()

        val accessToken = accessTokenRepository.fetchAccessToken(hostName, clientId, clientSecret, redirectUri, code)

        accessTokenRepository.cacheAccessToken(hostName, accessToken)
        accessTokenRepository.saveOwnAccount(hostName, accessToken)

        accessToken
    }
}
