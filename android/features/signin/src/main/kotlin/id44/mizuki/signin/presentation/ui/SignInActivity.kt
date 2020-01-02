package id44.mizuki.signin.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import id44.mizuki.bridges.signin.SignInView
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.signin.di.inject
import id44.mizuki.signin.presentation.model.SignInViewModelImpl
import javax.inject.Inject

class SignInActivity : InjectableReactActivity(), SignInView {
    @Inject
    internal lateinit var viewModel: SignInViewModelImpl

    override fun getMainComponentName(): String = "SignIn"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)

        viewModel.authorizeUri.observe(this, Observer(this::openAuthorizePage))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.onNewIntent(intent)
    }

    private fun openAuthorizePage(url: Uri) {
        Intent(Intent.ACTION_VIEW, url).takeIf {
            it.resolveActivity(packageManager) != null
        }?.let(this::startActivity) ?: viewModel.onNotFoundBrowser()
    }

    override fun showErrorMessage(message: String) = Toast.makeText(this, message, LENGTH_LONG).show()

    override fun openTimeline() {
        finish()
        startActivity(intentTo(Activities.Timeline))
    }

    /*
    private fun showErrorMessage(throwable: Throwable) {
        val message = when (throwable) {
            is AccessDeniedError -> getString(R.string.auth_error_access_denied)
            is AuthorizeError -> throwable.message ?: getString(R.string.auth_error_authorize)
            is BrowserAppNotFoundError -> getString(R.string.auth_error_browser_app_not_found)
            is UnknownHostException -> getString(R.string.auth_error_unknown_host)
            else -> throwable.message ?: getString(R.string.auth_error_unknown)
        }

        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
    */
}
