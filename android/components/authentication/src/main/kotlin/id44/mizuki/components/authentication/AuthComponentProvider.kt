package id44.mizuki.components.authentication

import android.app.Activity
import id44.mizuki.components.core.coreComponent

interface AuthComponentProvider {
    var authComponent: AuthComponent
}

fun Activity.buildAuthComponent(): AuthComponent {
    val authComponent = DaggerAuthComponent.builder()
        .coreComponent(application.coreComponent)
        .build()

    (application as AuthComponentProvider).authComponent = authComponent

    return authComponent
}
