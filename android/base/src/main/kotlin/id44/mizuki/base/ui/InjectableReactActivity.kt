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
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.instance
import kotlin.coroutines.CoroutineContext

abstract class InjectableReactActivity : ReactActivity(), CoroutineScope, KodeinAware {
    override lateinit var kodein: Kodein

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

    val host: ReactNativeHost by instance()
    val reactRootView: ReactRootView by instance()

    override fun createReactActivityDelegate() = object : ReactActivityDelegate(this, mainComponentName) {
        override fun getReactNativeHost(): ReactNativeHost = host
        override fun createRootView(): ReactRootView = reactRootView
    }
}
