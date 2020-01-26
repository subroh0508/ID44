package id44.mizuki.libraries.account.domain.usecase.fetchownaccount.di

import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCase
import id44.mizuki.libraries.account.domain.usecase.fetchownaccount.FetchOwnAccountUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val fetchOwnAccountUseCaseModule = Kodein.Module(name = "FetchOwnAccountUseCaseModule") {
    bind<FetchOwnAccountUseCase>() with singleton { FetchOwnAccountUseCaseImpl(instance()) }
}
