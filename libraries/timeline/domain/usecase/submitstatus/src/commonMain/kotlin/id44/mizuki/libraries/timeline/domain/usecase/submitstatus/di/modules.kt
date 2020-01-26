package id44.mizuki.libraries.timeline.domain.usecase.submitstatus.di

import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.SubmitStatusUseCase
import id44.mizuki.libraries.timeline.domain.usecase.submitstatus.SubmitStatusUseCaseImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val submitStatusUseCaseModule = Kodein.Module(name = "SubmitStatusUseCaseModule") {
    bind<SubmitStatusUseCase>() with singleton { SubmitStatusUseCaseImpl(instance()) }
}
