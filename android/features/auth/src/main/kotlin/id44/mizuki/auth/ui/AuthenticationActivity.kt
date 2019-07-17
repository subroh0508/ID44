package id44.mizuki.auth.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import id44.mizuki.auth.AuthenticationContract
import id44.mizuki.auth.R
import id44.mizuki.auth.generatePresenter
import id44.mizuki.auth.model.AuthenticationViewModel
import id44.mizuki.base.ui.ScopedActivity
import id44.mizuki.libraries.api.auth.Constants
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.coroutines.launch

class AuthenticationActivity : ScopedActivity(), AuthenticationContract.View {
    internal lateinit var presenter: AuthenticationContract.Presenter

    internal val viewModel: AuthenticationViewModel by lazy {
        ViewModelProviders.of(this)[AuthenticationViewModel::class.java]
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

        launch(coroutineContext) {
            val code = presenter.fetchAuthorizeCode(hostName)

            viewModel.postAccessToken(
                presenter.requestAccessToken(hostName, code)
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

    private fun Intent?.getAuthorizeCode(): Pair<String?, String?> {
        val uri = this?.data

        if (uri != null && uri.toString().startsWith(Constants.REDIRECT_URI)) {
            val code = uri.getQueryParameter("code")
            val error = uri.getQueryParameter("error")

            return code to error
        }

        return null to null
    }
}
