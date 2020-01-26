package id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.di

import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccounts.FetchOwnAccountsUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val fetchOwnAccountsUseCaseModule = Kodein.Module(name = "FetchOwnAccountUseCaseModule") {
    bind<FetchOwnAccountsUseCase>() with singleton { FetchOwnAccountsUseCaseImpl(instance()) }
}
