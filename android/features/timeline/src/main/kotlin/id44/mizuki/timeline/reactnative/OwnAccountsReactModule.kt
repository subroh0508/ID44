package id44.mizuki.timeline.reactnative

import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactMethod

interface OwnAccountsReactModule {
    @ReactMethod
    fun fetchOwnAccounts(promise: Promise)
}
