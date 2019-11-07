package id44.mizuki.auth.presentation.ui

import id44.mizuki.auth.presentation.RequireAuthContract
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.ScopedActivity
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class RequireAuthActivity : ScopedActivity(), RequireAuthContract.View {
    @Inject
    lateinit var httpsExceptionHandler: CoroutineExceptionHandler

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main + httpsExceptionHandler

    override fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }

    override fun showHttpErrorMessage(e: Throwable) = Unit
}
