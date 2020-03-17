package id44.mizuki.signin

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import id44.mizuki.shared.util.valueobject.Uri
import id44.mizuki.shared.util.valueobject.parse
import id44.mizuki.signin.di.MAIN_COMPONENT_NAME
import id44.mizuki.signin.di.inject
import id44.mizuki.signin.viewmodel.SignInViewModel
import org.kodein.di.generic.instance

class SignInActivity : InjectableReactActivity() {
    private val viewModel: SignInViewModel by instance()

    override fun getMainComponentName() = MAIN_COMPONENT_NAME

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)

        viewModel.authorizeUri.observe(this, Observer(this::openAuthorizePage))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        viewModel.onNewIntent(intent)
    }

    fun showErrorMessage(message: String) = Toast.makeText(this, message, LENGTH_LONG).show()

    fun openTimeline() {
        finish()
        startActivity(intentTo(Activities.Timeline))
    }

    private fun openAuthorizePage(url: Uri) {
        Intent(Intent.ACTION_VIEW, url.parse()).takeIf {
            it.resolveActivity(packageManager) != null
        }?.let(this::startActivity) ?: viewModel.onNotFoundBrowser()
    }
}
