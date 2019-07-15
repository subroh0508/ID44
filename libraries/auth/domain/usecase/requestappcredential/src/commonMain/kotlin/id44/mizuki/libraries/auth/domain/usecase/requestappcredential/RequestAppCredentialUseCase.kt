package id44.mizuki.libraries.auth.domain.usecase.requestappcredential

interface RequestAppCredentialUseCase {
    suspend fun execute(hostName: String): String
}