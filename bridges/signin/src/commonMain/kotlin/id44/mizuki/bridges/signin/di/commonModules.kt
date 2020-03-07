package id44.mizuki.bridges.signin.di

import id44.mizuki.api.di.mastodonAuthApiModule
import id44.mizuki.domain.signin.usecase.di.signInUseCaseModule
import id44.mizuki.infra.auth.repository.di.authRepositoryModule
import org.kodein.di.Kodein

val signInModule = Kodein.Module(name = "SignInModule") {
    import(mastodonAuthApiModule)
    import(authRepositoryModule)
    import(signInUseCaseModule)
}
