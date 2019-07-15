package id44.mizuki.auth.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import id44.mizuki.auth.AuthenticationConstract
import id44.mizuki.auth.R
import id44.mizuki.auth.generatePresenter
import id44.mizuki.base.ui.ScopedActivity
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.launch

class AuthenticationActivity : ScopedActivity(), AuthenticationConstract.View {
    lateinit var prenseter: AuthenticationConstract.Presenter

    private lateinit var authorizeDeferred: CompletableDeferred<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        prenseter = generatePresenter(this)

        authorize.setOnClickListener {
            launch(coroutineContext) {
                authorizeDeferred = CompletableDeferred()

                try {
                    val url = prenseter.buildAuthorizeUrl(hostName.text.toString())

                    val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

                    if (viewIntent.resolveActivity(packageManager) != null) {
                        startActivity(viewIntent)
                    } else {
                        authorizeDeferred.complete(null)
                    }

                    val code = authorizeDeferred.await() ?: return@launch

                    val accessToken = prenseter.requestAccessToken(hostName.text.toString(), code)

                    result.text = accessToken
                } catch(e: Throwable) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)

        val uri = intent?.data

        if (uri != null && uri.toString().startsWith("oauth2redirect://id44.mizuki/")) {
            val code = uri.getQueryParameter("code")
            val error = uri.getQueryParameter("error")

            authorizeDeferred.complete(if (error?.isNotBlank() == true) null else code)
        } else {
            authorizeDeferred.complete(null)
        }
    }
}
