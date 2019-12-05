package id44.mizuki.timeline.presentation.ui

import android.os.Bundle
import androidx.lifecycle.Observer
import id44.mizuki.auth.presentation.ui.RequireAuthReactActivity
import id44.mizuki.base.Activities
import id44.mizuki.base.intentTo
import id44.mizuki.bridges.timeline.TimelineView
import id44.mizuki.timeline.di.TimelineActivityComponent
import id44.mizuki.timeline.di.inject
import id44.mizuki.timeline.presentation.model.TimelineViewModel
import id44.mizuki.timeline.reactnative.emit
import javax.inject.Inject

class TimelineActivity : RequireAuthReactActivity(), TimelineView {
    @Inject
    lateinit var viewModel: TimelineViewModel

    override fun getMainComponentName(): String = "Timeline"

    override fun onCreate(savedInstanceState: Bundle?) {
        inject()

        super.onCreate(savedInstanceState)

        viewModel.status.observe(this, Observer {
            emit(reactInstanceManager.currentReactContext, it)
        })
    }

    override fun openAuthentication() {
        finish()
        startActivity(intentTo(Activities.Authentication))
    }

    override fun restart() {
        finish()
        startActivity(intent)
    }

    internal lateinit var timelineActivityComponent: TimelineActivityComponent
}
