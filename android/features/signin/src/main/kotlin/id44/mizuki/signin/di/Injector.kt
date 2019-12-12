package id44.mizuki.signin.di

import id44.mizuki.bridges.signin.di.buildSignInComponent
import id44.mizuki.libraries.shared.valueobject.Uri
import id44.mizuki.signin.R
import id44.mizuki.signin.presentation.ui.SignInActivity

fun SignInActivity.inject() {
    val clientName: String by lazy { getString(R.string.auth_client_name) }
    val redirectUri: Uri by lazy { Uri.parse("${getString(R.string.auth_oauth_scheme)}://$clientName/") }

    DaggerSignInActivityComponent.factory()
        .create(buildSignInComponent(), this, clientName, redirectUri)
        .inject(this)
}
