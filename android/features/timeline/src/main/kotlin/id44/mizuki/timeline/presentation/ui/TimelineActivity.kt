package id44.mizuki.timeline.presentation.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import id44.mizuki.auth.presentation.ui.RequireAuthReactActivity
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.timeline.di.TimelineActivityComponent
import id44.mizuki.timeline.di.inject
import id44.mizuki.timeline.presentation.model.OwnAccountsViewModel
import id44.mizuki.timeline.presentation.model.TimelineViewModel
import id44.mizuki.timeline.reactnative.emit
import javax.inject.Inject

class TimelineActivity : RequireAuthReactActivity() {
    @Inject
    lateinit var ownAccountsViewModel: OwnAccountsViewModel
    @Inject
    lateinit var viewModel: TimelineViewModel

    override fun getMainComponentName(): String = "Timeline"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)

        ownAccountsViewModel.openAuthentication.observe(this, Observer {
            finish()
            startActivity(intentTo(Activities.Authentication))
        })
        ownAccountsViewModel.restart.observe(this, Observer {
            finish()
            startActivity(intent)
        })
        viewModel.status.observe(this, Observer {
            emit(reactInstanceManager.currentReactContext, it)
        })
    }

    internal lateinit var timelineActivityComponent: TimelineActivityComponent
}
