package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken

interface RequestAccessTokenUseCase {
    suspend fun execute(hostName: String, clientId: String, clientSecret: String, code: String)
}
