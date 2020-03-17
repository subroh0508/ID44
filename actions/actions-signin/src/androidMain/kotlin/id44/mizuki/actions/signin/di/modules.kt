package id44.mizuki.bridges.signin.di

import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactNativeHost
import id44.mizuki.bridges.shared.ReactContextModuleProvider
import id44.mizuki.bridges.shared.di.baseViewModule
import id44.mizuki.bridges.signin.SignInNativeActions
import id44.mizuki.bridges.signin.SignInReactNativeHost
import id44.mizuki.bridges.signin.SignInView
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.scoped
import org.kodein.di.erased.singleton

fun signInViewModule(view: AppCompatActivity) = Kodein {
    import(signInModule)

    extend((view.application as KodeinAware).kodein)
    baseViewModule

    bind<SignInView>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton { view as SignInView }
    bind<ReactNativeHost>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        SignInReactNativeHost(instance(), instance())
    }
    bind<ReactContextModuleProvider>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        ReactContextModuleProvider { context -> SignInNativeActions(context, instance()) }
    }
}
