package id44.mizuki

import android.app.Application
import com.facebook.soloader.SoLoader
import id44.mizuki.bridges.signin.di.SignInComponent
import id44.mizuki.bridges.signin.di.SignInComponentProvider
import id44.mizuki.bridges.timeline.di.TimelineComponent
import id44.mizuki.bridges.timeline.di.TimelineComponentProvider
import id44.mizuki.components.core.CoreComponent
import id44.mizuki.components.core.CoreComponentProvider
import id44.mizuki.components.core.CoreModule
import id44.mizuki.components.core.DaggerCoreComponent
import kotlinx.serialization.json.Json

class MainApplication : Application(),
    CoreComponentProvider, SignInComponentProvider, TimelineComponentProvider {

    override lateinit var coreComponent: CoreComponent
    override lateinit var signInComponent: SignInComponent
    override lateinit var timelineComponent: TimelineComponent

    /*
    private val reactNativeHost: ReactNativeHost = object : ReactNativeHost(this) {
        override fun getPackages() = listOf(MainReactPackage(), VectorIconsPackage())
        override fun getUseDeveloperSupport() = BuildConfig.DEBUG
        override fun getJSMainModuleName() = "index"
    }

    override fun getReactNativeHost() = reactNativeHost
    */

    override fun onCreate() {
        super.onCreate()
        SoLoader.init(this, false)

        buildCoreComponent()
    }

    private fun buildCoreComponent() {
        coreComponent = DaggerCoreComponent.builder()
            .coreModule(
                CoreModule(
                    this,
                    BuildConfig.APPLICATION_ID, BuildConfig.VERSION_NAME,
                    Json.nonstrict
                )
            )
            .build()
    }
}
