package id44.mizuki.libraries.auth.domain.usecase.requestappcredential.di

import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val requestAppCredentialUseCaseModule = Kodein.Module(name = "RequestAppCredentialUseCaseModule") {
    bind<RequestAppCredentialUseCase>() with singleton { RequestAppCredentialUseCaseImpl(instance(), instance()) }
}
