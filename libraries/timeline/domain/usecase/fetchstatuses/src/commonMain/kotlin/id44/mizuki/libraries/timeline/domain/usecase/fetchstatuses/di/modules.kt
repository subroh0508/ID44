package id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.di

import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCase
import id44.mizuki.libraries.timeline.domain.usecase.fetchstatuses.FetchStatusesUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val fetchStatusesUseCaseModule = Kodein.Module(name = "FetchStatusesUseCaseModule") {
    bind<FetchStatusesUseCase>() with singleton { FetchStatusesUseCaseImpl(instance()) }
}
