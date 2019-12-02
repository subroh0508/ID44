package id44.mizuki.auth.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.CoroutineStart
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Job

interface RequireAuthContract {
    interface View {
        fun showHttpErrorMessage(e: Throwable)
        fun openAuthentication()
    }

    interface Presenter {
        fun onHttpError(e: Throwable)
    }

    interface ViewModel {
        fun launch(
            start: CoroutineStart = CoroutineStart.DEFAULT,
            block: suspend CoroutineScope.() -> Unit
        ): Job

        fun <T> async(
            start: CoroutineStart = CoroutineStart.DEFAULT,
            block: suspend CoroutineScope.() -> T
        ): Deferred<T>

        fun <T> liveData(
            timeoutInMs: Long = 5000L,
            block: suspend LiveDataScope<T>.() -> Unit
        ): LiveData<T>
    }
}
