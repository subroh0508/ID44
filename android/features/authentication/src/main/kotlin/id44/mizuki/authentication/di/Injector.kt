package id44.mizuki.authentication.di

import id44.mizuki.authentication.presentation.ui.AuthenticationActivity
import id44.mizuki.components.authentication.buildAuthComponent

fun AuthenticationActivity.inject() {
    authenticationActivityComponent = DaggerAuthenticationActivityComponent.builder()
        .authComponent(buildAuthComponent())
        .authenticationActivity(this)
        .build()

    authenticationActivityComponent.inject(this)
}
