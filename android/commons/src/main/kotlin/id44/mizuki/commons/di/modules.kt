package id44.mizuki.commons.di

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id44.mizuki.commons.viewmodel.RequireAuthViewModel
import id44.mizuki.commons.viewmodel.RequireAuthViewModelImpl
import org.kodein.di.DKodeinAware
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.bindings.WeakContextScope
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.scoped
import org.kodein.di.generic.singleton

private const val REQUIRE_AUTH_VIEW_MODEL_TAG = "REQUIRE_AUTH_VIEW_MODEL_TAG"

fun Kodein.MainBuilder.requireAuthViewModule(view: AppCompatActivity) {
    bind<RequireAuthViewModel>(REQUIRE_AUTH_VIEW_MODEL_TAG) with scoped(WeakContextScope.of<AppCompatActivity>()).singleton {
        val factory = RequireAuthViewModelImpl.Factory(instance())

        ViewModelProvider(view, factory)[RequireAuthViewModelImpl::class.java]
    }
}

fun KodeinAware.instanceAuthViewModule() = instance<RequireAuthViewModel>(REQUIRE_AUTH_VIEW_MODEL_TAG)
fun DKodeinAware.instanceAuthViewModule() = instance<RequireAuthViewModel>(REQUIRE_AUTH_VIEW_MODEL_TAG)
