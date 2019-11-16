package id44.mizuki.base.ui

import android.os.Bundle
import com.facebook.react.ReactActivity
import com.facebook.react.ReactActivityDelegate
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

abstract class ScopedReactActivity : ReactActivity(), CoroutineScope {
    protected lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        job = SupervisorJob()
    }

    override fun onDestroy() {
        super.onDestroy()
        job.cancel()
    }

    @Inject
    lateinit var host: dagger.Lazy<ReactNativeHost>
    @Inject
    lateinit var reactRootView: dagger.Lazy<ReactRootView>

    override fun createReactActivityDelegate() = object : ReactActivityDelegate(this, mainComponentName) {
        override fun getReactNativeHost(): ReactNativeHost = host.get()
        override fun createRootView(): ReactRootView = reactRootView.get()
    }
}
