package id44.mizuki

import android.app.Application
import android.os.Build
import com.facebook.soloader.SoLoader
import io.ktor.client.features.UserAgent
import kotlinx.serialization.json.Json
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.bind
import org.kodein.di.generic.singleton

class MainApplication : Application(), KodeinAware {
    override val kodein: Kodein = Kodein {
        bind<Application>() with singleton { this@MainApplication }
        bind<UserAgent>() with singleton {
            UserAgent("${BuildConfig.APPLICATION_ID}/${BuildConfig.VERSION_NAME} (Android ${Build.VERSION.RELEASE}; ${Build.PRODUCT})")
        }
        bind<Json>() with singleton { Json.nonstrict }
    }

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
    }
}
