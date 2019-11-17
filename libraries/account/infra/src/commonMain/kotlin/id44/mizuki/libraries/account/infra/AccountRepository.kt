package id44.mizuki.libraries.account.infra

interface AccountRepository {
    suspend fun fetchAccount(
        hostName: String
    )
}
