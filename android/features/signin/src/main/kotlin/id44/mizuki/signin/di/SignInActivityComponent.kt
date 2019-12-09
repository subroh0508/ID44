package id44.mizuki.signin.di

import dagger.BindsInstance
import dagger.Component
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.signin.di.SignInComponent
import id44.mizuki.signin.presentation.ui.SignInActivity

@ActivityScope
@Component(
    modules = [SignInActivityModule::class],
    dependencies = [SignInComponent::class]
)
interface SignInActivityComponent {
    @Component.Builder
    interface Builder {
        fun build(): SignInActivityComponent

        fun signInComponent(signInComponent: SignInComponent): Builder
        @BindsInstance fun signInActivity(activity: SignInActivity): Builder
    }

    fun inject(activity: SignInActivity)
}
