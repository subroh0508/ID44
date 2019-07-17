package id44.mizuki.auth.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id44.mizuki.auth.*
import id44.mizuki.auth.model.AuthenticationViewModel
import id44.mizuki.base.ui.ScopedActivity
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class AuthenticationActivity : ScopedActivity(), AuthenticationContract.View {
    internal lateinit var presenter: AuthenticationContract.Presenter

    internal val viewModel: AuthenticationViewModel by lazy {
        ViewModelProviders.of(this)[AuthenticationViewModel::class.java]
    }

    internal val authorizeErrorHandler: CoroutineExceptionHandler = CoroutineExceptionHandler { _, e ->
        when (e) {
            is AccessDeniedError -> showErrorMessage(getString(R.string.auth_error_access_denied))
            is AuthorizeError -> showErrorMessage(e.message ?: getString(R.string.auth_error_authorize))
            is BrowserAppNotFoundError -> showErrorMessage(getString(R.string.auth_error_browser_app_not_found))
            else -> showErrorMessage(e.message ?: getString(R.string.auth_error_unknown))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        presenter = generatePresenter(this)

        authorize.setOnClickListener { presenter.onClickAuthorize() }

        viewModel.accessToken.observe(this, Observer(presenter::onRequestedAccessToken))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val (code, error) = intent.getAuthorizeCode()

        presenter.onNewIntent(code, error)
    }

    override fun startOauth2Flow() {
        val hostName = hostName.text.toString()

        launch(coroutineContext + authorizeErrorHandler) {
            val code = presenter.fetchAuthorizeCode(hostName, clientName, redirectUri)

            viewModel.postAccessToken(
                presenter.requestAccessToken(hostName, redirectUri, code)
            )
        }
    }

    override fun openAuthorizePage(url: String) {
        Intent(Intent.ACTION_VIEW, Uri.parse(url)).takeIf {
            it.resolveActivity(packageManager) != null
        }?.let {
            startActivity(it)
        } ?: presenter.notifyBrowserNotFound()
    }

    override fun bindAccessToken(accessToken: String) {
        result.text = accessToken
    }

    override fun showErrorMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun Intent?.getAuthorizeCode(): Pair<String?, String?> {
        val uri = this?.data

        if (uri != null && uri.toString().startsWith(redirectUri)) {
            val code = uri.getQueryParameter(QUERY_CODE)
            val error = uri.getQueryParameter(QUERY_ERROR)

            return code to error
        }

        return null to null
    }

    private val clientName by lazy { getString(R.string.auth_client_name) }
    private val redirectUri by lazy { "${getString(R.string.auth_oauth_scheme)}://$clientName/" }

    companion object {
        private const val QUERY_CODE = "code"
        private const val QUERY_ERROR = "error"
    }
}
