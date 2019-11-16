package id44.mizuki.authentication.reactnative

import android.app.Application
import com.facebook.react.ReactPackage
import id44.mizuki.base.reactnative.BaseReactNativeHost

class AuthenticationReactNativeHost(
    app: Application,
    private val authenticationPackage: AuthenticationActivityPackage
) : BaseReactNativeHost(app) {
    override fun getPackages(): MutableList<ReactPackage> =
        super.getPackages().apply { add(authenticationPackage) }

    override fun getBundleAssetName() = "index.android.bundle"
    override fun getJSMainModuleName() = "components/auth/index"
}
