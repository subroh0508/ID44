package id44.mizuki.bridges.auth.di

import androidx.appcompat.app.AppCompatActivity
import id44.mizuki.bridges.auth.RequireAuthView
import id44.mizuki.bridges.shared.di.baseViewModule
import org.kodein.di.Kodein
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.erased.bind
import org.kodein.di.erased.scoped
import org.kodein.di.erased.singleton

fun Kodein.MainBuilder.requireAuthViewModule(view: AppCompatActivity) {
    baseViewModule

    bind<RequireAuthView>() with scoped(WeakContextScope.of<AppCompatActivity>()).singleton { view as RequireAuthView }
}
