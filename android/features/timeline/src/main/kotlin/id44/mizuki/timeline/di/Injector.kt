package id44.mizuki.timeline.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.dylanvann.fastimage.FastImageViewPackage
import com.facebook.react.ReactNativeHost
import com.facebook.react.ReactRootView
import com.oblador.vectoricons.VectorIconsPackage
import com.swmansion.gesturehandler.react.RNGestureHandlerPackage
import com.swmansion.reanimated.ReanimatedPackage
import id44.mizuki.bridges.timeline.di.timelineModule
import id44.mizuki.commons.di.instanceAuthViewModule
import id44.mizuki.commons.di.requireAuthViewModule
import id44.mizuki.commons.reactnativesupport.SimpleReactNativeHost
import id44.mizuki.timeline.TimelineActivity
import id44.mizuki.timeline.viewmodel.OwnAccountsViewModel
import id44.mizuki.timeline.viewmodel.StreamingViewModel
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

internal const val EVENT_APPEND_STATUS = "EVENT_APPEND_STATUS"
internal const val STREAM = "STREAM"
internal const val STATUS_VISIBILITY = "STATUS_VISIBILITY"

const val MAIN_COMPONENT_NAME = "Timeline"
const val BUNDLE_NAME = "index.android.bundle"
const val JS_MODULE_NAME = "frontend/components/timeline/index"

fun TimelineActivity.inject() {
    kodein = Kodein {
        import(timelineModule)

        extend((application as KodeinAware).kodein)

        requireAuthViewModule(this@inject)

        bind<ReactNativeHost>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            SimpleReactNativeHost(
                instance(), BUNDLE_NAME, JS_MODULE_NAME,
                nativeModules = listOf(TimelineReactModule(instance(), instance())),
                packages = listOf(
                    VectorIconsPackage(),
                    FastImageViewPackage(),
                    RNGestureHandlerPackage(),
                    ReanimatedPackage()
                )
            )
        }
        bind<StreamingViewModel.Factory>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            StreamingViewModel.Factory(instanceAuthViewModule(), null, instance(), instance())
        }
        bind<StreamingViewModel>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            ViewModelProvider(this@inject, instance<StreamingViewModel.Factory>())[StreamingViewModel::class.java]
        }
        bind<OwnAccountsViewModel.Factory>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            OwnAccountsViewModel.Factory(instanceAuthViewModule(), instance(), instance(), instance())
        }
        bind<OwnAccountsViewModel>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            ViewModelProvider(this@inject, instance<OwnAccountsViewModel.Factory>())[OwnAccountsViewModel::class.java]
        }
        bind<ReactRootView>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            ReactRootView(this@inject)
        }
    }
}
