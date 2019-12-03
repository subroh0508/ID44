package id44.mizuki.timeline.reactnative

import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import id44.mizuki.timeline.presentation.model.OwnAccountsViewModel

class TimelineActivityReactModule(
    reactContext: ReactApplicationContext,
    private val ownAccountsViewModel: OwnAccountsViewModel
) : ReactContextBaseJavaModule(reactContext),
        OwnAccountsReactModule by ownAccountsViewModel {
    override fun getName() = "TimelineContract"

    override fun getConstants() = mutableMapOf<String, Any>(
        EVENT_APPEND_STATUS to EVENT_APPEND_STATUS
    )
}
