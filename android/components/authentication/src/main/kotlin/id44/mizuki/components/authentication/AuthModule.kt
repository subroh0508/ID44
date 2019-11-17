package id44.mizuki.components.authentication

import dagger.Module
import id44.mizuki.libraries.api.auth.di.MastodonAuthApiModule
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.di.RequestAccessTokenUseCaseModule
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.di.RequestAppCredentialUseCaseModule
import id44.mizuki.libraries.auth.infra.di.AuthRepositoryModule

@Module(includes = [
    MastodonAuthApiModule::class,
    AuthRepositoryModule::class,
    RequestAppCredentialUseCaseModule::class,
    RequestAccessTokenUseCaseModule::class
])
class AuthModule
