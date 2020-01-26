package id44.mizuki.signin.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.facebook.react.ReactRootView
import id44.mizuki.bridges.signin.di.signInViewModule
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.signin.R
import id44.mizuki.signin.presentation.model.SignInViewModel
import id44.mizuki.signin.presentation.ui.SignInActivity
import org.kodein.di.Kodein
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

fun SignInActivity.inject() {
    val clientName: String by lazy { getString(R.string.auth_client_name) }
    val redirectUri: Uri by lazy { Uri.parse("${getString(R.string.auth_oauth_scheme)}://$clientName/") }

    kodein = Kodein {
        extend(signInViewModule(this@inject))

        bind<SignInViewModel.Factory>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            SignInViewModel.Factory(clientName, redirectUri, instance(), instance())
        }
        bind<SignInViewModel>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            ViewModelProvider(this@inject, instance<SignInViewModel.Factory>())[SignInViewModel::class.java]
        }
        bind<ReactRootView>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            ReactRootView(this@inject)
        }
    }
}
