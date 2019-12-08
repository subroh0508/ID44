package id44.mizuki.auth.presentation

import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import id44.mizuki.bridges.auth.RequireAuthView
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

abstract class RequireAuthReactActivity : InjectableReactActivity(), RequireAuthView {
    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }
}
