package id44.mizuki.auth

import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import id44.mizuki.bridges.auth.RequireAuthView

abstract class RequireAuthReactActivity : InjectableReactActivity(), RequireAuthView {
    override fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }
}
