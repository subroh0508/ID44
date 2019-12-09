package id44.mizuki.bridges.signin.di

import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactPackage
import dagger.Binds
import dagger.Module
import id44.mizuki.base.scope.ActivityScope
import id44.mizuki.bridges.signin.SignInPackage
import id44.mizuki.bridges.signin.SignInReactNativeHost
import id44.mizuki.bridges.signin.SignInView

@Module
abstract class SignInViewModule<in V: SignInView> {
    @Binds
    @ActivityScope
    abstract fun bindSignInView(view: V): SignInView

    @Binds
    @ActivityScope
    internal abstract fun bindSignInPackage(`package`: SignInPackage): ReactPackage

    @Binds
    @ActivityScope
    internal abstract fun bindReactNativeHost(host: SignInReactNativeHost): ReactNativeHost
}
