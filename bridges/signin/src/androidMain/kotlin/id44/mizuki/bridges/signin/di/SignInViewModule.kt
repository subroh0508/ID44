package id44.mizuki.bridges.signin.di

import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import dagger.Binds
import dagger.Module
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.signin.SignInPackage
import id44.mizuki.bridges.signin.SignInReactNativeHost

@Module
abstract class SignInViewModule {
    @Binds
    @ActivityScope
    internal abstract fun bindSignInPackage(`package`: SignInPackage): ReactPackage

    @Binds
    @ActivityScope
    internal abstract fun bindReactNativeHost(host: SignInReactNativeHost): ReactNativeHost
}
