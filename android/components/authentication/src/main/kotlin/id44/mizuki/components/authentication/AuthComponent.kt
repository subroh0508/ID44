package id44.mizuki.components.authentication

import android.app.Application
import dagger.Component
import id44.mizuki.base.scope.ModuleScope
import id44.mizuki.components.core.CoreComponent
import id44.mizuki.libraries.auth.domain.usecase.requestaccesstoken.RequestAccessTokenUseCase
import id44.mizuki.libraries.auth.domain.usecase.requestappcredential.RequestAppCredentialUseCase

@ModuleScope
@Component(
    modules = [AuthModule::class],
    dependencies = [CoreComponent::class]
)
interface AuthComponent {
    @Component.Builder
    interface Builder {
        fun build(): AuthComponent

        fun coreComponent(coreComponent: CoreComponent): Builder
    }

    fun provideApp(): Application
    fun provideRequestAppCredentialUseCase(): RequestAppCredentialUseCase
    fun provideRequestAccessTokenUseCase(): RequestAccessTokenUseCase
}
