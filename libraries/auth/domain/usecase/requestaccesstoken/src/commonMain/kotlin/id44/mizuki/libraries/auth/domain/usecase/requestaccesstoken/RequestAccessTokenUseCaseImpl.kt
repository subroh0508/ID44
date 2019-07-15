package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepository

class RequestAccessTokenUseCaseImpl(
    private val appCredentialRepository: AppCredentialRepository,
    private val accessTokenRepository: AccessTokenRepository
) : RequestAccessTokenUseCase {
    override suspend fun execute(hostName: String, code: String): String {
        val clientId = appCredentialRepository.getClientId(hostName) ?: throw IllegalStateException()
        val clientSecret = appCredentialRepository.getClientSecret(hostName) ?: throw IllegalStateException()

        val accessToken = accessTokenRepository.fetchAccessToken(hostName, clientId, clientSecret, code)

        accessTokenRepository.cacheAccessToken(hostName, accessToken)

        return accessToken
    }
}
