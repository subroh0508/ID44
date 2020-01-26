package id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.di

import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCase
import id44.mizuki.libraries.timeline.domain.usecase.unsubscribe.TimelineUnsubscribeUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val timelineUnsubscribeUseCaseModule = Kodein.Module(name = "TimelineUnsubscribeUseCaseModule") {
    bind<TimelineUnsubscribeUseCase>() with singleton { TimelineUnsubscribeUseCaseImpl(instance(), instance()) }
}
