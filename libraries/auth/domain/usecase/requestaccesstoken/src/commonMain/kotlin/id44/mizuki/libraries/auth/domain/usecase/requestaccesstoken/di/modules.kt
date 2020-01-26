package id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.di

import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val requestAccessTokenUseCaseModule = Kodein.Module(name = "RequestAccessTokenUseCaseModule") {
    bind<RequestAccessTokenUseCase>() with singleton { RequestAccessTokenUseCaseImpl(instance(), instance()) }
}
