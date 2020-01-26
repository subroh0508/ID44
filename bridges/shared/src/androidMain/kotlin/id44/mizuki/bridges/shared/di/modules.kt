package id44.mizuki.bridges.shared.di

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager
import id44.mizuki.bridges.shared.ReactContextModuleProvider
import org.kodein.di.Kodein
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.scoped
import org.kodein.di.erased.singleton

val Kodein.MainBuilder.baseViewModule get() = bind<ReactPackage>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
    object : ReactPackage {
        override fun createViewManagers(
            reactContext: ReactApplicationContext
        ): MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()

        override fun createNativeModules(
            reactContext: ReactApplicationContext
        ): MutableList<NativeModule> = mutableListOf(instance<ReactContextModuleProvider>().provide(reactContext))
    }
}
