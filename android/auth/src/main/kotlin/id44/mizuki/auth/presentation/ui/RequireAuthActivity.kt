package id44.mizuki.auth.presentation.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import id44.mizuki.auth.presentation.viewmodel.RequireAuthViewModel
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import javax.inject.Inject

abstract class RequireAuthActivity : ReactActivity() {
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

    @Inject
    lateinit var host: ReactNativeHost
    @Inject
    lateinit var reactRootView: ReactRootView

    override fun createReactActivityDelegate() = object : ReactActivityDelegate(this, mainComponentName) {
        override fun getReactNativeHost(): ReactNativeHost = host
        override fun createRootView(): ReactRootView = reactRootView
    }
}
