package id44.mizuki.auth.di

import dagger.BindsInstance
import dagger.Component
import id44.mizuki.auth.presentation.ui.AuthenticationActivity
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.components.auth.AuthComponent

@ActivityScope
@Component(
    modules = [AuthenticationActivityModule::class],
    dependencies = [AuthComponent::class]
)
interface AuthenticationActivityComponent {
    @Component.Builder
    interface Builder {
        fun build(): AuthenticationActivityComponent

        fun authComponent(authComponent: AuthComponent): Builder
        @BindsInstance fun authenticationActivity(activity: AuthenticationActivity): Builder
    }

    fun inject(activity: AuthenticationActivity)
}
