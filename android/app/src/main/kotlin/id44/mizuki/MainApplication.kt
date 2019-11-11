package id44.mizuki

import android.app.Application
import com.facebook.react.ReactApplication
import com.facebook.react.ReactNativeHost
import com.facebook.react.shell.MainReactPackage
import com.facebook.soloader.SoLoader
import com.oblador.vectoricons.VectorIconsPackage
import id44.mizuki.components.authentication.AuthComponent
import id44.mizuki.components.authentication.AuthComponentProvider
import id44.mizuki.components.core.CoreComponent
import id44.mizuki.components.core.CoreComponentProvider
import id44.mizuki.components.core.CoreModule
import id44.mizuki.components.core.DaggerCoreComponent
import id44.mizuki.components.timeline.TimelineComponent
import id44.mizuki.components.timeline.TimelineComponentProvider
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json

class MainApplication : Application(),
    CoreComponentProvider, AuthComponentProvider, TimelineComponentProvider {

    override lateinit var coreComponent: CoreComponent
    override lateinit var authComponent: AuthComponent
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
                    KotlinxSerializer(Json.nonstrict)
                )
            )
            .build()
    }
}
