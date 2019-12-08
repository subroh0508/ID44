package id44.mizuki.auth.presentation.ui

import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import id44.mizuki.bridges.auth.RequireAuthView
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class RequireAuthReactActivity : InjectableReactActivity(), RequireAuthView {
    @Inject
    internal lateinit var httpExceptionHandler: CoroutineExceptionHandler

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + httpExceptionHandler

    override fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }

    open fun showHttpErrorMessage(e: Throwable) = Unit
}
