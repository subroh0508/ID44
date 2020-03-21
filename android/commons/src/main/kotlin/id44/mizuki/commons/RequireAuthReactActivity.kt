package id44.mizuki.commons

import android.os.Bundle
import androidx.lifecycle.Observer
import id44.mizuki.commons.di.instanceAuthViewModule
import id44.mizuki.commons.reactnativesupport.InjectableReactActivity
import id44.mizuki.commons.viewmodel.RequireAuthViewModel

abstract class RequireAuthReactActivity : InjectableReactActivity()  {
    private val viewModel: RequireAuthViewModel by instanceAuthViewModule()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.restartAuthorization.observe(this, Observer { openAuthentication() })
    }

    private fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }
}
