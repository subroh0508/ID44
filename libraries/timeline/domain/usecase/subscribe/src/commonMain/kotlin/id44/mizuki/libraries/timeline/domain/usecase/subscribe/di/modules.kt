package id44.mizuki.libraries.timeline.domain.usecase.subscribe.di

import id44.mizuki.libraries.timeline.domain.usecase.subscribe.TimelineSubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.subscribe.TimelineSubscribeUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val timelineSubscribeUseCaseModule = Kodein.Module(name = "TimelineSubscribeUseCaseModule") {
    bind<TimelineSubscribeUseCase>() with singleton { TimelineSubscribeUseCaseImpl(instance(), instance()) }
}
