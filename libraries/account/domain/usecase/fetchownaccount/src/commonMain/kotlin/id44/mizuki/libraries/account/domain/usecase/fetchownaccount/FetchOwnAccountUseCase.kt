package id44.mizuki.libraries.account.domain.usecase.fetchownaccount

import id44.mizuki.shared.model.account.Account

interface FetchOwnAccountUseCase {
    suspend fun execute(): Account
}
