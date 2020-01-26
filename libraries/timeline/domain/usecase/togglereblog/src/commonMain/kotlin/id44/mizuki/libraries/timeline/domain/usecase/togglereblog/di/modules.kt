package id44.mizuki.libraries.timeline.domain.usecase.togglereblog.di

import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.ToggleReblogUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglereblog.ToggleReblogUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val toggleReblogUseCaseModule = Kodein.Module(name = "ToggleReblogUseCaseModule") {
    bind<ToggleReblogUseCase>() with singleton { ToggleReblogUseCaseImpl(instance()) }
}
