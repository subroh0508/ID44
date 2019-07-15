package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

import id44.mizuki.libraries.auth.infra.repository.AccessTokenRepository

class RequestAccessTokenUseCaseImpl(
    private val accessTokenRepository: AccessTokenRepository
) : RequestAccessTokenUseCase {
    override suspend fun execute(hostName: String, clientId: String, clientSecret: String, code: String) {
        val accessToken = accessTokenRepository.fetchAccessToken(hostName, clientId, clientSecret, code)

        accessTokenRepository.cacheAccessToken(hostName, accessToken)
    }
}
