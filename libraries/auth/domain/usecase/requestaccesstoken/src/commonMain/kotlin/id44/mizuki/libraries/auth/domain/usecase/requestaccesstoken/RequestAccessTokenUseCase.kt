package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

interface RequestAccessTokenUseCase {
    suspend fun execute(hostName: String, code: String): String
}
