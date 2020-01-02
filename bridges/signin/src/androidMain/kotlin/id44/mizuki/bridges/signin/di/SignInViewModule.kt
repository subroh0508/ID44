package id44.mizuki.bridges.signin.di

import com.facebook.react.ReactNativeHost
import dagger.Binds
import dagger.Module
import dagger.Provides
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.shared.ReactContextModuleProvider
import id44.mizuki.bridges.shared.di.BaseViewModule
import id44.mizuki.bridges.signin.SignInNativeActions
import id44.mizuki.bridges.signin.SignInReactNativeHost
import id44.mizuki.bridges.signin.SignInView
import id44.mizuki.bridges.signin.SignInViewModel

@Module
abstract class SignInViewModule<in V: SignInView> : BaseViewModule() {
    @Binds
    @ActivityScope
    internal abstract fun bindView(view: V): SignInView

    @Binds
    @ActivityScope
    internal abstract fun bindReactNativeHost(host: SignInReactNativeHost): ReactNativeHost

    @Module
    companion object {
        @JvmStatic
        @Provides
        @ActivityScope
        internal fun provideReactContextModuleProvider(
            view: SignInView,
            viewModel: SignInViewModel
        ) = ReactContextModuleProvider { context -> SignInNativeActions(context, view, viewModel) }
    }
}
