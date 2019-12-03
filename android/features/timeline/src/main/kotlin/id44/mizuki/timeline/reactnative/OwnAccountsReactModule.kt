package id44.mizuki.timeline.reactnative

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactMethod

interface OwnAccountsReactModule {
    @ReactMethod
    fun openAuthentication()

    @ReactMethod
    fun fetchOwnAccounts(promise: Promise)

    @ReactMethod
    fun switchAccount(host: String, id: String)
}
