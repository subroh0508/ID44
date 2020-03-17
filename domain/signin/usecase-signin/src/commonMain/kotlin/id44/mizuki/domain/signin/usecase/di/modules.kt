package id44.mizuki.domain.signin.usecase.di

import id44.mizuki.api.di.mastodonAuthApiModule
import id44.mizuki.domain.signin.usecase.RequestAccessTokenUseCase
import id44.mizuki.domain.signin.usecase.RequestAccessTokenUseCaseImpl
import id44.mizuki.domain.signin.usecase.RequestAppCredentialUseCase
import id44.mizuki.domain.signin.usecase.RequestAppCredentialUseCaseImpl
import id44.mizuki.infra.auth.repository.di.authRepositoryModule
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val signInModule = Kodein.Module(name = "SignInModule") {
    import(mastodonAuthApiModule)
    import(authRepositoryModule)
    import(signInUseCaseModule)
}

// publicじゃないとKotlin/JS環境で動作しない
val signInUseCaseModule = Kodein.Module(name = "SignInUseCaseModule") {
    bind<RequestAccessTokenUseCase>() with singleton { RequestAccessTokenUseCaseImpl(instance(), instance()) }
    bind<RequestAppCredentialUseCase>() with singleton { RequestAppCredentialUseCaseImpl(instance(), instance()) }
}
