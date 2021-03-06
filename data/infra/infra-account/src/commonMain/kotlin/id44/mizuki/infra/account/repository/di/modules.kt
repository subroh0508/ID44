package id44.mizuki.infra.account.repository.di

import id44.mizuki.infra.account.repository.AccountRepository
import id44.mizuki.infra.account.repository.AccountRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val accountRepositoryModule = Kodein.Module("AccountRepositoryModule") {
    bind<AccountRepository>() with singleton { AccountRepositoryImpl(instance(), instance()) }
}
