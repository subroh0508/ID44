package id44.mizuki.libraries.timeline.infra.di

import id44.mizuki.libraries.timeline.infra.repository.StatusRepository
import id44.mizuki.libraries.timeline.infra.repository.StatusRepositoryImpl
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepository
import id44.mizuki.libraries.timeline.infra.repository.StreamingRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.erased.bind
import org.kodein.di.erased.instance
import org.kodein.di.erased.singleton

val statusRepositoryModule = Kodein.Module(name = "StatusRepositoryModule") {
    bind<StatusRepository>() with singleton { StatusRepositoryImpl(instance(), instance()) }
}

val streamingRepositoryModule = Kodein.Module(name = "StreamingRepositoryModule") {
    bind<StreamingRepository>() with singleton { StreamingRepositoryImpl(instance()) }
}
