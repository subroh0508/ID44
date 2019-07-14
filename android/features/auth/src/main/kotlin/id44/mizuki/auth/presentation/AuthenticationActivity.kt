package id44.mizuki.auth.presentation

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import id44.mizuki.api.enums.Scope
import id44.mizuki.auth.R
import id44.mizuki.auth.infra.MastodonAuthRepository
import id44.mizuki.base.ui.ScopedActivity
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import kotlinx.android.synthetic.main.activity_authentication.*
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.launch

class AuthenticationActivity : ScopedActivity() {
    private val httpClient = HttpClient(Android) {
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer()
        }
    }

    private val repository: MastodonAuthRepository by lazy {
        MastodonAuthRepository(httpClient)
    }

    private lateinit var authorizeDeferred: CompletableDeferred<String?>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_authentication)

        authorize.setOnClickListener {
            launch(coroutineContext) {
                val code = authorize(
                    "...",
                    "...",
                    "oauth2redirect://id44.mizuki/",
                    Scope.READ, Scope.WRITE, Scope.FOLLOW, Scope.PUSH
                ).await() ?: return@launch

                repository.hostName = hostName.text.toString()

                val accessToken = repository.requestAccessToken(
                    "...",
                    "...",
                    "oauth2redirect://id44.mizuki/",
                    code
                )

                result.text = accessToken.toString()
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

    private fun authorize(
        clientId: String,
        clientSecret: String,
        redirectUri: String,
        vararg scope: Scope
    ): Deferred<String?> {
        authorizeDeferred = CompletableDeferred()

        val url = buildString {
            append("https://")
            append(hostName.text.toString())
            append("/oauth/authorize?")
            append("response_type=code&")
            append(
                mapOf(
                    "client_id" to clientId,
                    "client_secret" to clientSecret,
                    "redirect_uri" to redirectUri,
                    "scope" to scope.joinToString(" ") { it.toString().toLowerCase() }
                ).map { (k, v) -> "$k=${Uri.encode(v)}" }.joinToString("&")
            )
        }

        val viewIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))

        if (viewIntent.resolveActivity(packageManager) != null) {
            startActivity(viewIntent)
        } else {
            authorizeDeferred.complete(null)
        }

        return authorizeDeferred
    }
}
