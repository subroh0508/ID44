package id44.mizuki.auth.di

import id44.mizuki.auth.presentation.ui.AuthenticationActivity
import id44.mizuki.components.auth.buildAuthComponent

fun AuthenticationActivity.inject() {
    authenticationActivityComponent = DaggerAuthenticationActivityComponent.builder()
        .authComponent(buildAuthComponent())
        .authenticationActivity(this)
        .build()

    authenticationActivityComponent.inject(this)
}
