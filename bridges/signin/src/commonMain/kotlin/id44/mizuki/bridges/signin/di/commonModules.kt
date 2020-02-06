package id44.mizuki.bridges.signin.di

import id44.mizuki.libraries.api.di.mastodonAuthApiModule
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.di.requestAccessTokenUseCaseModule
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.di.requestAppCredentialUseCaseModule
import id44.mizuki.libraries.auth.infra.repository.di.authRepositoryModule
import org.kodein.di.Kodein

val signInModule = Kodein.Module(name = "SignInModule") {
    import(mastodonAuthApiModule)
    import(authRepositoryModule)
    import(requestAppCredentialUseCaseModule)
    import(requestAccessTokenUseCaseModule)
}
