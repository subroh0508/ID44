package id44.mizuki.authentication.presentation.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import id44.mizuki.authentication.R
import id44.mizuki.authentication.di.AuthenticationActivityComponent
import id44.mizuki.authentication.di.inject
import id44.mizuki.authentication.presentation.AuthenticationContract
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.ScopedReactActivity
import id44.mizuki.libraries.shared.valueobject.AccessToken
import id44.mizuki.libraries.shared.valueobject.HostName
import id44.mizuki.libraries.shared.valueobject.Uri
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationActivity : ScopedReactActivity(), AuthenticationContract.View {
    @Inject
    internal lateinit var presenter: AuthenticationContract.Presenter
    @Inject
    internal lateinit var viewModel: AuthenticationContract.Model
    @Inject
    internal lateinit var authorizeErrorHandler: CoroutineExceptionHandler

    override fun getMainComponentName(): String = "Auth"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)

        viewModel.accessToken.observe(this, Observer(presenter::onRequestedAccessToken))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val (code, error) = intent.getAuthorizeCode()

        presenter.onNewIntent(code, error)
    }

    override fun startOauth2Flow() {
        launch(authorizeErrorHandler) {
            val hostName = viewModel.hostName.value ?: HostName("")

            val code = presenter.fetchAuthorizeCode(hostName, clientName, redirectUri)

            viewModel.bindAccessToken(
                presenter.requestAccessToken(hostName, redirectUri, code)
            )
        }
    }

    override fun openAuthorizePage(url: Uri) {
        Intent(Intent.ACTION_VIEW, url).takeIf {
            it.resolveActivity(packageManager) != null
        }?.let(this::startActivity) ?: presenter.notifyBrowserNotFound()
    }

    override fun bindAccessToken(token: AccessToken) {
        startActivity(
            intentTo(Activities.Timeline).apply {
                putExtra("hostname", viewModel.hostName.value?.value)
            }
        )
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun Intent?.getAuthorizeCode(): Pair<String?, String?> {
        val uri = this?.data

        if (uri != null && uri.toString().startsWith(redirectUri.toString())) {
            val code = uri.getQueryParameter(QUERY_CODE)
            val error = uri.getQueryParameter(QUERY_ERROR)

            return code to error
        }

        return null to null
    }

    private val clientName by lazy { getString(R.string.auth_client_name) }
    private val redirectUri by lazy { id44.mizuki.libraries.shared.valueobject.Uri.parse("${getString(R.string.auth_oauth_scheme)}://$clientName/") }

    companion object {
        private const val QUERY_CODE = "code"
        private const val QUERY_ERROR = "error"
    }

    internal lateinit var authenticationActivityComponent: AuthenticationActivityComponent
}
