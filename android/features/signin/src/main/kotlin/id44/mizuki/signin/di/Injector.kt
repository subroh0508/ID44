package id44.mizuki.signin.di

import id44.mizuki.bridges.signin.di.buildSignInComponent
import id44.mizuki.signin.presentation.ui.SignInActivity

fun SignInActivity.inject() {
    signInActivityComponent = DaggerSignInActivityComponent.builder()
        .signInComponent(buildSignInComponent())
        .signInActivity(this)
        .build()

    signInActivityComponent.inject(this)
}
