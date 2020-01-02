package id44.mizuki.signin.di

import androidx.lifecycle.ViewModelProvider
import com.facebook.react.ReactRootView
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.bridges.signin.SignInViewModel
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.signin.di.SignInViewModule
import id44.mizuki.signin.presentation.model.SignInViewModelImpl
import id44.mizuki.signin.presentation.ui.SignInActivity

@Module
abstract class SignInActivityModule : SignInViewModule<SignInActivity>() {
    @Binds
    @ActivityScope
    internal abstract fun bindSignInViewModelFactory(factory: SignInViewModelImpl.Factory): ViewModelProvider.NewInstanceFactory

    @Binds
    @ActivityScope
    internal abstract fun bindSignInViewModel(viewModel: SignInViewModelImpl): SignInViewModel

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideSignInViewModelImpl(
            activity: SignInActivity,
            factory: SignInViewModelImpl.Factory
        ): SignInViewModelImpl = ViewModelProvider(activity, factory)[SignInViewModelImpl::class.java]

        @JvmStatic
        @Provides
        @ActivityScope
        fun provideReactRootView(activity: SignInActivity) = ReactRootView(activity)
    }
}
