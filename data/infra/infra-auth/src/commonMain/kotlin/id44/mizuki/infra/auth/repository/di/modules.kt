package id44.mizuki.infra.auth.repository.di

import id44.mizuki.api.CredentialProvider
import id44.mizuki.infra.auth.repository.*
import id44.mizuki.infra.auth.repository.AccessTokenRepositoryImpl
import id44.mizuki.infra.auth.repository.AccountCredentialRepositoryImpl
import id44.mizuki.infra.auth.repository.AppCredentialRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val accessTokenRepositoryModule = Kodein.Module(name = "AccessTokenRepositoryModule") {
    bind<CredentialProvider>() with singleton { instance<AccessTokenRepository>() as AccessTokenRepositoryImpl }
    bind<AccessTokenRepository>() with singleton { AccessTokenRepositoryImpl(instance(), instance()) }
}

val authRepositoryModule = Kodein.Module(name = "AuthRepositoryModule") {
    bind<AppCredentialRepository>() with singleton { AppCredentialRepositoryImpl(instance(), instance()) }
    bind<AccountCredentialRepository>() with singleton { AccountCredentialRepositoryImpl(instance(), instance(), instance()) }
}
