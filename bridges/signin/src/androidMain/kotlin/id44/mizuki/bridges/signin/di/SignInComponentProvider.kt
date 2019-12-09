package id44.mizuki.bridges.signin.di

import android.app.Activity
import id44.mizuki.components.core.coreComponent

interface SignInComponentProvider {
    var signInComponent: SignInComponent
}

fun Activity.buildSignInComponent(): SignInComponent {
    val signInComponent = DaggerSignInComponent.builder()
        .coreComponent(application.coreComponent)
        .build()

    (application as SignInComponentProvider).signInComponent = signInComponent

    return signInComponent
}
