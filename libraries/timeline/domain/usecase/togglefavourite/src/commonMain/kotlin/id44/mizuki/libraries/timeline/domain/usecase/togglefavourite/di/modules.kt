package id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.di

import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.ToggleFavouriteUseCase
import id44.mizuki.libraries.timeline.domain.usecase.togglefavourite.ToggleFavouriteUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val toggleFavouriteUseCaseModule = Kodein.Module(name = "ToggleFavouriteUseCaseModule") {
    bind<ToggleFavouriteUseCase>() with singleton { ToggleFavouriteUseCaseImpl(instance()) }
}
