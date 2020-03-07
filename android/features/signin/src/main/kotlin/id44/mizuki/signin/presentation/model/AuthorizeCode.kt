package id44.mizuki.signin.presentation.model

import android.content.Intent
import id44.mizuki.shared.valueobject.Uri

inline class AuthorizeCode(private val pair: Pair<String, String?>) {
    val code get() = pair.first
    val error get() = pair.second

    val isSuccess get() = code.isNotBlank()

    companion object {
        fun fromIntent(intent: Intent?, redirectUri: Uri): AuthorizeCode? = intent?.data?.let { uri ->
            if (uri.toString().startsWith(redirectUri.toString())) {
                val code = uri.getQueryParameter(QUERY_CODE) ?: ""
                val error = uri.getQueryParameter(QUERY_ERROR)

                return@let AuthorizeCode(code to error)
            }

            null
        }

        private const val QUERY_CODE = "code"
        private const val QUERY_ERROR = "error"
    }
}
