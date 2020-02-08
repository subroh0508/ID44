package id44.mizuki.signin.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import id44.mizuki.bridges.signin.SignInView
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.libraries.shared.valueobject.parse
import id44.mizuki.signin.di.inject
import id44.mizuki.signin.presentation.model.SignInViewModel
import kotlinx.coroutines.launch
import org.kodein.di.generic.instance

class SignInActivity : InjectableReactActivity(), SignInView {
    private val viewModel: SignInViewModel by instance()

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

    override fun startOauth2Flow(host: String, resolve: (Any?) -> Unit, reject: (Throwable) -> Unit) {
        viewModel.viewModelScope.launch {
            runCatching { viewModel.startOauth2Flow(HostName(host)) }
                .onSuccess { resolve.invoke(null) }
                .onFailure {
                    it.printStackTrace()
                    reject.invoke(it)
                }
        }
    }

    override fun showErrorMessage(message: String) = Toast.makeText(this, message, LENGTH_LONG).show()

    override fun openTimeline() {
        finish()
        startActivity(intentTo(Activities.Timeline))
    }

    private fun openAuthorizePage(url: Uri) {
        Intent(Intent.ACTION_VIEW, url.parse()).takeIf {
            it.resolveActivity(packageManager) != null
        }?.let(this::startActivity) ?: viewModel.onNotFoundBrowser()
    }
}
