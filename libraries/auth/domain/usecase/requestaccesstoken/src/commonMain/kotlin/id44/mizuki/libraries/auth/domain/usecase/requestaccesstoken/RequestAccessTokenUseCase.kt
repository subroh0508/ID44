package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

interface RequestAccessTokenUseCase {
    suspend fun execute(hostName: String, redirectUri: String, code: String): String
}
