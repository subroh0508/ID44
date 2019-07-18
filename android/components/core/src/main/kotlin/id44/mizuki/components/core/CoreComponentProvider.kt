package id44.mizuki.components.core

import android.app.Application

interface CoreComponentProvider {
    var coreComponent: CoreComponent
}

val Application.coreComponent: CoreComponent
    get() = (this as CoreComponentProvider).coreComponent
