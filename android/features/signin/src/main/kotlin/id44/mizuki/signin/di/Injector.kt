package id44.mizuki.signin.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dylanvann.fastimage.FastImageViewPackage
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.oblador.vectoricons.VectorIconsPackage
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import id44.mizuki.domain.signin.usecase.di.signInModule
import id44.mizuki.commons.reactnativesupport.SimpleReactNativeHost
import id44.mizuki.shared.util.valueobject.Uri
import id44.mizuki.signin.R
import id44.mizuki.signin.SignInActivity
import id44.mizuki.signin.viewmodel.SignInViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

const val MAIN_COMPONENT_NAME = "SignIn"
const val BUNDLE_NAME = "index.android.bundle"
const val JS_MODULE_NAME = "frontend/components/signin/index"

fun SignInActivity.inject() {
    val clientName: String by lazy { getString(R.string.auth_client_name) }
    val redirectUri: Uri by lazy { Uri("${getString(R.string.auth_oauth_scheme)}://$clientName/") }

    kodein = Kodein {
        extend((application as KodeinAware).kodein)

        import(signInModule)

        bind<ReactNativeHost>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            SimpleReactNativeHost(
                instance(), BUNDLE_NAME, JS_MODULE_NAME,
                nativeModules = listOf(SignInReactModule(instance())),
                packages = listOf(VectorIconsPackage(), FastImageViewPackage(), RNGestureHandlerPackage(), ReanimatedPackage())
            )
        }
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
