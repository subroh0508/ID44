package id44.mizuki.signin.di

import dagger.BindsInstance
import dagger.Component
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.signin.di.SignInComponent
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.signin.presentation.ui.SignInActivity

@ActivityScope
@Component(
    modules = [SignInActivityModule::class],
    dependencies = [SignInComponent::class]
)
interface SignInActivityComponent {
    @Component.Factory
    interface Builder {
        fun create(
            signInComponent: SignInComponent,
            @BindsInstance activity: SignInActivity,
            @BindsInstance clientName: String,
            @BindsInstance redirectUri: Uri
        ): SignInActivityComponent
    }

    fun inject(activity: SignInActivity)
}
