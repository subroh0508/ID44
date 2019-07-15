package id44.mizuki.libraries.auth.domain.usecase.requestappcredential

import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository
import id44.mizuki.libraries.auth.infra.repository.AppCredentialRepository

class RequestAppCredentialUseCaseImpl(
    private val appCredentialRepository: AppCredentialRepository,
    private val accessTokenRepository: AccessTokenRepository
) : RequestAppCredentialUseCase {
    override suspend fun execute(hostName: String): String {
        val (clientId, clientSecret) = appCredentialRepository.fetchAppCredential(hostName)

        appCredentialRepository.cacheAppCredential(hostName, clientId, clientSecret)

        return accessTokenRepository.buildAuthorizeUrl(
            hostName, clientId, clientSecret
        )
    }
}