package id44.mizuki.timeline.di

import androidx.appcompat.app.AppCompatActivity
import com.facebook.react.ReactRootView
import id44.mizuki.bridges.timeline.di.timelineViewModule
import id44.mizuki.timeline.TimelineActivity
import org.kodein.di.Kodein
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

fun TimelineActivity.inject() {
    kodein = Kodein {
        extend(timelineViewModule(this@inject))

        bind<ReactRootView>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
            ReactRootView(this@inject)
        }
    }
}
