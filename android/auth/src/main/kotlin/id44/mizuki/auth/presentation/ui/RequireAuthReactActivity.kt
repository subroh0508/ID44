package id44.mizuki.auth.presentation.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import id44.mizuki.auth.presentation.viewmodel.RequireAuthViewModel
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.base.ui.InjectableReactActivity
import javax.inject.Inject

abstract class RequireAuthReactActivity : InjectableReactActivity() {
    @Inject
    internal lateinit var viewModel: RequireAuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.httpException.observe(this, Observer(this::showHttpErrorMessage))
        viewModel.openAuthentication.observe(this, Observer { openAuthentication() })
    }

    private fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }

    open fun showHttpErrorMessage(e: Throwable) = Unit
}
