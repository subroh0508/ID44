package id44.mizuki.signin.di

import androidx.lifecycle.ViewModelProvider
import com.facebook.react.ReactRootView
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.signin.di.SignInViewModule
import id44.mizuki.signin.presentation.model.SignInViewModel
import id44.mizuki.signin.presentation.ui.SignInActivity

@Module
abstract class SignInActivityModule : SignInViewModule<SignInActivity>() {
    @Binds
    @ActivityScope
    internal abstract fun bindSignInViewModelFactory(factory: SignInViewModel.Factory): ViewModelProvider.NewInstanceFactory

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideSignInViewModel(
            activity: SignInActivity,
            factory: SignInViewModel.Factory
        ): SignInViewModel = ViewModelProvider(activity, factory)[SignInViewModel::class.java]

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactRootView(activity: SignInActivity) = ReactRootView(activity)
    }
}
