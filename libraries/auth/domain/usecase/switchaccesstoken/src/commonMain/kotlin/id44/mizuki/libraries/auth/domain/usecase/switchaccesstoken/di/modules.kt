package id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.di

import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.switchaccesstoken.SwitchAccessTokenUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val switchAccessTokenUseCaseModule = Kodein.Module(name = "SwitchAccessTokenUseCaseModule") {
    bind<SwitchAccessTokenUseCase>() with singleton { SwitchAccessTokenUseCaseImpl(instance()) }
}
