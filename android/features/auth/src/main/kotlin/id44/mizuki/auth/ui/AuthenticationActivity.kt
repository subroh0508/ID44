package id44.mizuki.auth.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.facebook.react.ReactInstanceManager
import id44.mizuki.auth.AuthenticationContract
import id44.mizuki.auth.R
import id44.mizuki.auth.di.AuthenticationActivityComponent
import id44.mizuki.auth.di.inject
import id44.mizuki.base.ui.ScopedActivity
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch
import javax.inject.Inject

class AuthenticationActivity : ScopedActivity(), AuthenticationContract.View {
    @Inject
    internal lateinit var presenter: AuthenticationContract.Presenter
    @Inject
    internal lateinit var viewModel: AuthenticationContract.Model
    @Inject
    internal lateinit var authorizeErrorHandler: CoroutineExceptionHandler
    @Inject
    internal lateinit var reactInstanceManager: ReactInstanceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        inject()

        reactRootView.startReactApplication(
            reactInstanceManager,
            "Auth"
        )
        viewModel.accessToken.observe(this, Observer(presenter::onRequestedAccessToken))
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val (code, error) = intent.getAuthorizeCode()

        presenter.onNewIntent(code, error)
    }

    override fun startOauth2Flow() {
        val hostName = viewModel.hostName.value ?: ""

        launch(coroutineContext + authorizeErrorHandler) {
            val code = presenter.fetchAuthorizeCode(hostName, clientName, redirectUri)

            viewModel.bindAccessToken(
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
        //result.text = accessToken
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

    internal lateinit var authenticationActivityComponent: AuthenticationActivityComponent
}
