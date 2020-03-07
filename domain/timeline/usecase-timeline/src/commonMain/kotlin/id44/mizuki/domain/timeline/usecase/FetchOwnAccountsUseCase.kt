package id44.mizuki.domain.timeline.usecase

import id44.mizuki.shared.model.account.Account

interface FetchOwnAccountsUseCase {
    fun execute(): List<Account>
}
