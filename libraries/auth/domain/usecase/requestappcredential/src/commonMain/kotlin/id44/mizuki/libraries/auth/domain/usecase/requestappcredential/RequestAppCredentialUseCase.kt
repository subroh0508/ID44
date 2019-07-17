package id44.mizuki.libraries.auth.domain.usecase.requestappcredential

interface RequestAppCredentialUseCase {
    suspend fun execute(hostName: String, clientName: String, redirectUri: String): String
}